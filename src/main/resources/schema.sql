

CREATE TABLE IF NOT EXISTS public.EXCHANGE_RATE (
  id         INT           AUTO_INCREMENT PRIMARY KEY,
  origin_currency  VARCHAR (3) NOT NULL,
  target_currency  VARCHAR (3) NOT NULL,
  exchange_rate       DECIMAL(20, 2)           NOT NULL
);



