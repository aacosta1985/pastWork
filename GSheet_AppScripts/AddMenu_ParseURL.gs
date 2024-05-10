function onOpen() {
  var ui = SpreadsheetApp.getUi();
  ui.createMenu('Helper Menu')
      .addItem('Parse URL from Clipboard', 'parseURLFromClipboard')
      .addItem('No Response Filter', 'filterColumns')
      .addToUi();
}

function parseURLFromClipboard() {
  var ui = SpreadsheetApp.getUi();
  var clipboard = ui.prompt('Paste clipboard: ').getResponseText();
  if (isURL(clipboard)) {
    var ui = SpreadsheetApp.getUi();
    var response = ui.alert('Confirm Action', 'A URL has been detected in the clipboard. Do you want to proceed?', ui.ButtonSet.YES_NO);
    if (response == ui.Button.YES) {
      var url = clipboard;
      parseURLandInsertRow(url);
    }
  } else {
    var ui = SpreadsheetApp.getUi();
    ui.alert('Error', 'Not a URL', ui.ButtonSet.OK);
  }
}

function isURL(str) {
  // Regular expression to check if a string is a valid URL
  var pattern = new RegExp('^(https?|ftp):\\/\\/([\\w\\.-]+)\\.+');
  
  return pattern.test(str);
}

function parseURLandInsertRow(url) {
  var sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  
  // Fetch the contents of the URL
  var response = UrlFetchApp.fetch(url);
  var content = response.getContentText();
  
  // Parse the details from the content (you may need to customize this part based on the structure of the URL content)
  var details = parseContent(content);
  
  // Insert a new row with the parsed details
  var newRow = [details[0], details[1], url]; // Adding the URL value to the end of the details array
  sheet.appendRow(newRow);
}

function parseContent(content) {
  // Implement your logic to parse the content and extract the details
  // For demonstration purposes, let's say we're extracting a title and description
  var title = "Sample Title";
  var description = "Sample Description";
  
  return [title, description]; // Return an array containing the parsed details
}