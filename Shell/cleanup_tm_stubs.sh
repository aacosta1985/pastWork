#!/bin/bash

cd /Volumes/.timemachine/FDEA1F30-E582-46DA-B771-7FD2519A6AF8/ || { echo "Failed to cd into backup directory"; exit 1; }

echo "Building list of current tmutil-recognized backups..."
tmutil listbackups > /tmp/tm_valid_backups.txt

echo "Scanning for orphaned 64-byte stub folders..."
echo ""

for d in */; do
  d="${d%/}"
  size=$(stat -f%z "$d")
  if [ "$size" -eq 64 ]; then
    if grep -q "/$d/$d\$" /tmp/tm_valid_backups.txt; then
      echo "OK (keeping):        $d"
    else
      echo "ORPHANED - removing: $d"
      sudo rmdir "$d"
      if [ $? -eq 0 ]; then
        echo "  -> removed successfully"
      else
        echo "  -> FAILED to remove (folder may not be empty, or permission issue) - left untouched"
      fi
    fi
  fi
done

rm -f /tmp/tm_valid_backups.txt
echo ""
echo "Done."
