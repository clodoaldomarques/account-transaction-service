CREATE TABLE Transaction(
	transaction_id uuid PRIMARY KEY,
	account_id uuid NOT NULL REFERENCES Account,
	operation_type varchar(30) NOT NULL,
	amount numeric(15,2) NOT NULL,
	event_date timestamp NOT NULL
);