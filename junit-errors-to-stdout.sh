#!/bin/bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

ROOTDIR="$1"
if [ -z "$ROOTDIR" ]; then
	ROOTDIR="."
fi
echo 'Formatting results...'
FILES=$(find "$ROOTDIR" -path '*/build/test-results/*.xml')
if [ -n "$FILES" ]; then
	for file in $FILES; do
		echo "Formatting $file"
		if [ -f "$file" ]; then
			echo '====================================================='
			xsltproc "$DIR/junit-xml-format-errors.xsl" "$file"
		fi
	done
	echo '====================================================='
else
	echo 'No */build/test-results/*.xml files found with failing tests.'
fi