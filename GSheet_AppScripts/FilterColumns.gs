function filterColumns() {
  var sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  var range = sheet.getDataRange();
  var values = range.getValues();
  var numRows = range.getNumRows();
  var numCols = range.getNumColumns();
  
  var filter = sheet.getFilter();

  // Check if filters are already set on columns Status and Date Found
  var columnStatusIndex = getColumnIndexByHeader(values, 'Status');
  var columnDateFoundIndex = getColumnIndexByHeader(values, 'Date Found');
  
  if (filter && columnStatusIndex !== -1 && columnDateFoundIndex !== -1) {
    // If filters are already set, clear them
    filter.remove();
  } else {
    // If filters are not set, apply new filters

    // Find column indices for columns Status and Date Found
    var columnStatusIndex = -1;
    var columnDateFoundIndex = -1;
    for (var i = 0; i < numCols; i++) {
      if (values[0][i] === 'Status') {
        columnStatusIndex = i + 1; // Adding 1 to match A1 notation
      }
      if (values[0][i] === 'Date Found') {
        columnDateFoundIndex = i + 1; // Adding 1 to match A1 notation
      }
    }

    // If both columns Status and Date Found are found, apply filters
    if (columnStatusIndex !== -1 && columnDateFoundIndex !== -1) {
      var today = new Date();
      var thirtyDaysAgo = new Date();
      thirtyDaysAgo.setDate(today.getDate() - 30);

      var rangeToFilter = sheet.getRange(1, 1, numRows, numCols);
      var filterSettings = {
        '1': {
          'criteria': {
            '1': {
              'hiddenValues': ['']
            }
          }
        },
        [columnStatusIndex]: {
          'criteria': {
            '1': {
              'visibleValues': ['Applied']
            }
          }
        },
        [columnDateFoundIndex]: {
          'criteria': {
            '1': {
                // TODO: Update this reference as it currently breaks the macro
                'whenDateBefore': SpreadsheetApp.FilterCriteria.RelativeDate.PAST_MONTH,
            }
          }
        }
      };
      rangeToFilter.createFilter().setColumnFilterCriteria(1, filterSettings);
    } else {
      Logger.log("Columns Status and/or Date Found not found.");
    }
  }
}

function getColumnIndexByHeader(values, header) {
  for (var i = 0; i < values[0].length; i++) {
    if (values[0][i] === header) {
      return i + 1; // Adding 1 to match A1 notation
    }
  }
  return -1; // Return -1 if the header is not found
}
