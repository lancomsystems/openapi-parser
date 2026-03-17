#!/usr/bin/env bash

if [[ "${BASH_SOURCE[0]}" == "$0" ]]; then
    echo "Error: This script must be sourced:" >&2
    echo "  source ./load-secrets.sh" >&2
    exit 1
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SECRETS_FILE="$SCRIPT_DIR/secrets.sops.yaml"

if [[ ! -f "$SECRETS_FILE" ]]; then
    echo "Error: $SECRETS_FILE not found" >&2
    return 1
fi

if ! command -v sops &>/dev/null; then
    echo "Error: sops is not installed" >&2
    return 1
fi

echo "Loading secrets from $SECRETS_FILE ..."
while IFS= read -r line; do
    if [[ "$line" =~ ^([A-Z_]+):[[:space:]]+(.+)$ ]]; then
        key="${BASH_REMATCH[1]}"
        value="${BASH_REMATCH[2]}"
        export "$key"="$value"
        echo "  $key=***"
    fi
done < <(sops decrypt "$SECRETS_FILE")

echo ""
echo "Test publish (without release):"
echo "  ./gradlew publishToSonatype"
