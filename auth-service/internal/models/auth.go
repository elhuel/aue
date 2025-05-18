package models

type AuthResponse struct {
	Token string `json:"token"`
}

type Claims struct {
	UserID int    `json:"user_id"`
	Role   string `json:"role"`
}
