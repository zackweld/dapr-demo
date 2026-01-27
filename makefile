include validate.mk

##################################################
# Main targets
##################################################
MM_SHELL ?= bash -c
.PHONY: all test_all_quickstarts
all: install_mm validate

# Run all tests at once
test_all_quickstarts: test_java_quickstarts

# Update Java SDK version in all quickstarts
# Usage: make update_java_sdk_version VERSION=1.12.0
.PHONY: update_java_sdk_version
update_java_sdk_version:
	@if [ -z "$(VERSION)" ]; then \
		echo "Error: VERSION parameter is required. Usage: make update_java_sdk_version VERSION=1.12.0"; \
		exit 1; \
	fi
	@echo "Updating Dapr packages to version $(VERSION) in all Java projects..."
	@# Process standard SDK quickstarts
	@echo "Processing SDK quickstarts..."
	@building_blocks=$$(find . -maxdepth 1 -mindepth 1 -type d); \
	for building_block in $$building_blocks; do \
		if [ -d "$$building_block/java/sdk" ]; then \
			echo "Checking $$building_block/java/sdk for pom.xml files"; \
			POM_FILES=$$(find "$$building_block/java/sdk" -name "pom.xml"); \
			if [ -n "$$POM_FILES" ]; then \
				for POM in $$POM_FILES; do \
					POM_DIR=$$(dirname "$$POM"); \
					echo "Processing: $$POM"; \
					if grep -q "<groupId>io.dapr</groupId>" "$$POM"; then \
						echo "  Found io.dapr dependency in $$POM"; \
						echo "  Updating to version $(VERSION)"; \
						(cd "$$POM_DIR" && mvn versions:use-dep-version -Dincludes=io.dapr:dapr-sdk -DdepVersion=$(VERSION) -DgenerateBackupPoms=false -q) || \
						echo "  Failed to update io.dapr:dapr-sdk in $$POM"; \
						echo "  Updated dependency: "; \
						grep -A2 "<groupId>io.dapr</groupId>" "$$POM"; \
					else \
						echo "  No io.dapr dependency found in $$POM"; \
					fi; \
				done; \
			else \
				echo "No pom.xml files found in $$building_block/java/sdk"; \
			fi; \
		fi; \
	done
	@echo "Java SDK update complete! Please verify changes and run tests before committing."

# Test Java quickstarts
.PHONY: test_java_quickstarts
test_java_quickstarts:
	@echo "Testing all Java quickstarts..."
	@building_blocks=$$(find . -maxdepth 1 -mindepth 1 -type d); \
	for building_block in $$building_blocks; do \
		for variant in "http" "sdk"; do \
			if [ ! -d "$$building_block/java/$$variant" ]; then \
				echo "$$building_block/java/$$variant does not exist."; \
			else \
				echo "Validating $$building_block/java/$$variant quickstart"; \
				(cd $$building_block/java/$$variant && make validate) || echo "Validation failed for $$building_block/java/$$variant"; \
			fi; \
		done; \
	done
	@echo "Java quickstart testing complete!"