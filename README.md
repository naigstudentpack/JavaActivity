# 📋 Student Task Manager

A JavaFX desktop application for managing tasks with Supabase database integration.

## ✨ Features

- **User Authentication** - Login system with database validation
- **Task Management** - Add, mark as done, and delete tasks
- **Supabase Integration** - Cloud-based PostgreSQL database
- **User-specific Tasks** - Each user sees their own tasks
- **Modern UI** - Clean and responsive JavaFX interface

## 🛠️ Technologies Used

- **Java 21** - Core programming language
- **JavaFX 21** - Desktop GUI framework
- **Supabase** - Backend database (PostgreSQL)
- **Maven** - Dependency management
- **Git & GitHub** - Version control

## 📦 Database Schema

### Users Table
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

git clone https://github.com/naigstudentpack/JavaActivity.git
