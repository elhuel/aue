package main

import (
	"auth-service/internal/database"
	"auth-service/internal/handlers"
	"log"
	"net/http"
	"os"
)

func main() {

	db, err := database.NewPostgres(
		os.Getenv("DB_HOST"),
		os.Getenv("DB_PORT"),
		os.Getenv("DB_USER"),
		os.Getenv("DB_PASSWORD"),
		os.Getenv("DB_NAME"),
	)
	if err != nil {
		log.Fatal("Database connection failed:", err)
	}

	handlers.InitAuthHandlers(db)

	log.Println("Auth service started on :8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}
