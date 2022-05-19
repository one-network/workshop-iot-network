all: data-collector user-dashboard

.PHONY: data-collector
data-collector:
	$(MAKE) -C data-collector

.PHONY: user-dashboard
user-dashboard:
	$(MAKE) -C user-dashboard

.PHONY: clean
clean:
	$(MAKE) -C data-collector clean
	$(MAKE) -C user-dashboard clean

.PHONY: start
start:
	docker compose up --build -d

.PHONY: stop
stop:
	docker compose stop

.PHONY: destroy
destroy:
	docker compose down

.PHONY: logs
logs:
	docker compose logs -f --tail=10
