package database

import (
	"auth-service/internal/models"
	"database/sql"
	"fmt"

	_ "github.com/lib/pq"
)

type DB interface {
	CreateUser(*models.User) error
	GetUserByEmail(string) (*models.User, error)
}

type Postgres struct {
	db *sql.DB
}

func NewPostgres(host, port, user, password, dbname string) (*Postgres, error) {
	connStr := fmt.Sprintf(
		"host=%s port=%s user=%s password=%s dbname=%s sslmode=disable",
		host, port, user, password, dbname,
	)

	db, err := sql.Open("postgres", connStr)
	if err != nil {
		return nil, err
	}

	if err = db.Ping(); err != nil {
		return nil, err
	}

	return &Postgres{db: db}, nil
}
func (p *Postgres) CreateUser(user *models.User) error {
	_, err := p.db.Exec(
		"INSERT INTO users(email, password_hash, role) VALUES($1, $2, $3)",
		user.Email, user.Password, user.Role,
	)
	return err
}

func (p *Postgres) GetUserByEmail(email string) (*models.User, error) {
	user := &models.User{}
	err := p.db.QueryRow(
		"SELECT id, email, password_hash, role FROM users WHERE email = $1",
		email,
	).Scan(&user.ID, &user.Email, &user.Password, &user.Role)

	return user, err
}
