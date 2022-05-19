dist/bundle.js: node_modules/.marker $(shell find src -type f) webpack.config.js
	npm run build

node_modules/.marker: package.json package-lock.json
	npm install
	touch node_modules/.marker

.PHONY: clean
clean:
	npm run clean

.PHONY: dist
dist:
	NODE_ENV=production npm run build