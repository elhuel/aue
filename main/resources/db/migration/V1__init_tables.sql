CREATE TABLE places (
    id BIGSERIAL PRIMARY KEY,
    number INT NOT NULL UNIQUE CHECK (number BETWEEN 1 AND 20),
    description VARCHAR(255) NOT NULL
);

CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    place_id BIGINT NOT NULL REFERENCES places(id),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL CHECK (end_time = start_time + INTERVAL '1 hour'),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);