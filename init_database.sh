#!/bin/bash

# Database initialization script for Book Management System
# Creates 4 tables with relationships

DB_HOST="localhost"
DB_USER="notion"
DB_PASS="255455"
DB_NAME="notiondb"

echo "========================================="
echo "Initializing Database: $DB_NAME"
echo "========================================="

# Connect to MySQL and execute SQL queries
mysql -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" << 'EOF'

-- Drop existing tables if they exist (for fresh start)
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS authors;

-- Create authors table
CREATE TABLE IF NOT EXISTS authors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(100),
    birth_year INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create publishers table
CREATE TABLE IF NOT EXISTS publishers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(100),
    founded_year INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create books table with foreign keys
CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_year INT,
    publisher_id INT NOT NULL,
    category_id INT,
    price DECIMAL(10, 2),
    page_count INT,
    image_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE RESTRICT,
    FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE RESTRICT,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    INDEX idx_author_id (author_id),
    INDEX idx_publisher_id (publisher_id),
    INDEX idx_category_id (category_id),
    INDEX idx_title (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create indexes for better query performance
CREATE INDEX idx_isbn ON books(isbn);
CREATE INDEX idx_publication_year ON books(publication_year);

-- Insert sample data
INSERT INTO authors (name, country, birth_year) VALUES
('J.K. Rowling', 'United Kingdom', 1965),
('George R.R. Martin', 'United States', 1948),
('Stephen King', 'United States', 1947);

INSERT INTO publishers (name, country, founded_year) VALUES
('Bloomsbury Publishing', 'United Kingdom', 1986),
('Bantam Books', 'United States', 1945),
('Doubleday', 'United States', 1897);

INSERT INTO categories (name, description) VALUES
('Fantasy', 'Fantasy novels and stories'),
('Science Fiction', 'Science fiction and speculative fiction'),
('Mystery', 'Mystery and detective novels');

INSERT INTO books (title, author_id, isbn, publication_year, publisher_id, category_id, price, page_count) VALUES
('Harry Potter and the Philosopher\'s Stone', 1, '978-0747532699', 1997, 1, 1, 15.99, 223),
('A Game of Thrones', 2, '978-0553103540', 1996, 2, 1, 18.99, 694),
('The Shining', 3, '978-0385512165', 1977, 3, 3, 16.99, 447);

-- Display table structures
SHOW TABLES;

EOF

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "✓ Database initialization completed successfully!"
    echo "========================================="
    echo ""
    echo "Created tables:"
    echo "  - authors"
    echo "  - publishers"
    echo "  - categories"
    echo "  - books (with relationships)"
else
    echo "✗ Error occurred during database initialization"
    exit 1
fi
