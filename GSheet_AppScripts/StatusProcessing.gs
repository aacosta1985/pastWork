// Many of these scripts are relying on specific references within a sheet; however, those are templated rows or headers in a file
// The original script was capturing and collating results from over 30 test suites and run simultaneously to publish results once a week
function onOpen() {
  var ui = SpreadsheetApp.getUi();
  ui.createMenu('Testing')
      .addItem('Create Full Reg Batch', 'Create_Full_Batch_Updated')
      .addSeparator()
      .addItem('Create Smoke Batch', 'Create_Smoke_Batch')
      .addSeparator()  
      .addItem('Create Test Report', 'Create_Test_Report')
      .addSeparator()    
      .addItem('Publish Test Results', 'Publish_Test_Results')
      .addSeparator()
      .addItem('Test Execution', 'Test_Execution')
      .addToUi();
  Clear_Test_Report();
};

function Clear_Test_Report() {
  var ss = SpreadsheetApp.getActive();
  var sheet = ss.getSheetByName('Test_Report_Template');
  var sheet2 = ss.getSheetByName('testing_results');
  sheet.getRange('AU1').clearContent();
  sheet.getRange('AV1').clearContent();
  sheet.getRange('AW1').clearContent();
  
  //sheet2.getRange('A1').clearContent();
  //sheet2.getRange('B1').clearContent();  
  //sheet2.getRange('B3').clearContent();  
};

function Create_Test_Report() {
  
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet();
  var ss = SpreadsheetApp.setActiveSheet(spreadsheet.getSheetByName('Test_Report_Template'));
  var source = SpreadsheetApp.getActiveSpreadsheet().getSheetByName('testing_results');
  var dest = SpreadsheetApp.getActiveSpreadsheet();
  
  var appName = source.getRange('B1').copyTo(dest.getRange('AU1').activate(), {contentsOnly:true});
  var appType = source.getRange('A1').copyTo(dest.getRange('AV1').activate(), {contentsOnly:true});
  var batchId = source.getRange('B3').copyTo(dest.getRange('AW1').activate(), {contentsOnly:true});

  dest.getRange('A1').activate();
 
};

function Publish_Test_Results() {
    var spreadsheet = SpreadsheetApp.getActiveSpreadsheet();
    var dest = SpreadsheetApp.setActiveSheet(spreadsheet.getSheetByName('historical_results'));
    var source = SpreadsheetApp.getActiveSpreadsheet().getSheetByName('testing_results');
//    var dest = SpreadsheetApp.getActiveSpreadsheet();
    var dlr=dest.getLastRow()+1;
    
    var regressionType = source.getRange('E5').copyTo(dest.getRange('A'+dlr).activate(), {contentsOnly:true});
    var appName = source.getRange('B1').copyTo(dest.getRange('B'+dlr).activate(), {contentsOnly:true});
    var appType = source.getRange('A1').copyTo(dest.getRange('C'+dlr).activate(), {contentsOnly:true});
    var appVersion = source.getRange('C9').copyTo(dest.getRange('D'+dlr).activate(), {contentsOnly:true});
    var batchId = source.getRange('B3').copyTo(dest.getRange('E'+dlr).activate(), {contentsOnly:true});
    var passRate = source.getRange('B4').copyTo(dest.getRange('F'+dlr).activate(), {contentsOnly:true});   
    var exRate = source.getRange('C5').copyTo(dest.getRange('G'+dlr).activate(), {contentsOnly:true});   
    var openTickets = source.getRange('B6').copyTo(dest.getRange('H'+dlr).activate(), {contentsOnly:true}); 
    var lastWorkDate = source.getRange('D5').copyTo(dest.getRange('I'+dlr).activate(), {contentsOnly:true});     

};


function Create_Full_Batch_Updated() {
  var ui = SpreadsheetApp.getUi() // Or DocumentApp or FormApp.
  var response = ui.prompt('Enter Batch Details', 'BatchID', ui.ButtonSet.OK_CANCEL);
  // Process the user's response.
  if (response.getSelectedButton() == ui.Button.OK) {
    //Set Batch ID
    var id = response.getResponseText();
    PropertiesService.getScriptProperties().setProperty('batchId', id);
    //Set formatted date
    var dt = new Date();
    var formatDt = Utilities.formatDate(dt, "MST", "dd-MMM-yyyy");       
    createNewFullBatch_Updated(id,formatDt);
    } else {
      Logger.log('The user clicked the close button');
    }

};

function createNewFullBatch_Updated(id,formatDt)
{ 
  var ss = SpreadsheetApp.getActiveSpreadsheet();
  var sourceSheetName = SpreadsheetApp.getActiveSheet().getName();
  var lr=ss.getLastRow();
  var sRange = ss.getRange("A2:M"+lr);
  
  var short = sourceSheetName.split("_",2);
  var dest = String(short[0])+"_"+String(short[1])+"_raw";
  PropertiesService.getScriptProperties().setProperty('target', dest);
  
  
  var data=sRange.getValues();
  var postData=new Array(); 
  
 for (var i=0;i<data.length;i++)
  {
   
 var innerPostData=new Array();
    if(data[i][5]=='Y')
    {
      innerPostData.push(id);
      innerPostData.push(formatDt);
      innerPostData.push(data[i][0]);
      innerPostData.push(data[i][1]);
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push('No Run');
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push(data[i][7]);        
  postData.push(innerPostData);
    }
  

 }
    var len=postData.length;    
    var destinationSheetName=ss.getSheetByName(PropertiesService.getScriptProperties().getProperty('target'));
    var dlr=destinationSheetName.getLastRow()+1;
    var dsr=dlr+len-1;
    var destinationRange=destinationSheetName.getRange("A"+dlr+":K"+dsr);
  
  destinationRange.setValues(postData); 
};

function Create_Smoke_Batch() {
  var ui = SpreadsheetApp.getUi() // Or DocumentApp or FormApp.
  var response = ui.prompt('Enter Batch Details', 'BatchID', ui.ButtonSet.OK_CANCEL);
  // Process the user's response.
  if (response.getSelectedButton() == ui.Button.OK) {
    //Set Batch ID
    var id = response.getResponseText();
    PropertiesService.getScriptProperties().setProperty('batchId', id);
    //Set formatted date
    var dt = new Date();
    var formatDt = Utilities.formatDate(dt, "MST", "dd-MMM-yyyy");

    PropertiesService.getScriptProperties().setProperty('newDt', formatDt);     
    createNewSmokeBatch(id, formatDt);
    } else {
      Logger.log('The user clicked the close button');
    }
};

function updateJIRA() {
  var sheet = SpreadsheetApp.getActiveSheet();
  // Fetch the range of cells with data
  var lr = sheet.getLastRow();
  sheet.getRange("I2:I" + lr).clear();  
  var dataRange = sheet.getRange("H2:H" + lr);
  // Fetch values for each row in the Range.
  var data = dataRange.getValues();
  var arrayLength = data.length;
  Logger.log(PropertiesService.getScriptProperties().getProperty('jiraAuth'));

  for (var i = 0; i < arrayLength; i++) {
    if (data[i] == null) {
          sheet.getRange("I" + (i+2)).setValue('');
          SpreadsheetApp.flush();
    } else {

    if (typeof PropertiesService.getScriptProperties().getProperty('jiraAuth') != "undefined") {
      try {
        var jiraData = data[i];
        // TODO: Add JIRA string 
        var jiraTicket = jiraData.toString().replace("INSERT JIRA STRING HERE", "");
        //TODO: add JIRA API 
        var jiraURL = 'ADD JIRA API URL' + jiraTicket + '?fields=status';
        var headers = {
          "Authorization": "Basic " + PropertiesService.getScriptProperties().getProperty('jiraAuth')
        };
        var params = {
          "headers": headers,
          'muteHttpExceptions': true
        };
        var response = UrlFetchApp.fetch(jiraURL, params);
        var jiraInfo = JSON.parse(response.getContentText());
        Logger.log(jiraInfo.fields.status.name);
        if (jiraInfo.fields.status.name !== null) {
          sheet.getRange("I" + (i+2)).setValue(jiraInfo.fields.status.name);
          SpreadsheetApp.flush();
        } else {
          sheet.getRange("I" + (i+2)).setValue('');
          SpreadsheetApp.flush();
        }
      } catch (e) {
        // modify cell to record error
        Logger.log(e);
      }
      
    } else {
      // Trigger alert windows to prompt for username and password  
      var ui = SpreadsheetApp.getUi();
      var response = ui.prompt('Enter JIRA Credentials', 'Username?', ui.ButtonSet.OK_CANCEL);


      // Process the user's response.
      if (response.getSelectedButton() == ui.Button.OK) {
        const username = response.getResponseText();
        var response2 = ui.prompt('Enter JIRA Credentials', 'Password?', ui.ButtonSet.OK_CANCEL);
        if (response2.getSelectedButton() == ui.Button.OK) {
          const password = response2.getResponseText();
          var auth = Utilities.base64Encode(username + ':' + password)
          PropertiesService.getScriptProperties().setProperty('jiraAuth', auth);
          addJira();
        } //endJiraPassword 
      } //endJiraResponse
    }//endIfElse
  }//endfor
  }

}
  
function refreshToken(){
 var ui = SpreadsheetApp.getUi();
  PropertiesService.getScriptProperties().deleteProperty('jiraAuth');
      var response = ui.prompt('Enter JIRA Credentials', 'Username?', ui.ButtonSet.OK_CANCEL);


      // Process the user's response.
      if (response.getSelectedButton() == ui.Button.OK) {
        const username = response.getResponseText();
        var response2 = ui.prompt('Enter JIRA Credentials', 'Password?', ui.ButtonSet.OK_CANCEL);
        if (response2.getSelectedButton() == ui.Button.OK) {
          const password = response2.getResponseText();
          var auth = Utilities.base64Encode(username + ':' + password)
          PropertiesService.getScriptProperties().setProperty('jiraAuth', auth);
        }
      }
}

function addJIRA() {
  var sheet = SpreadsheetApp.getActiveSheet();
  // Fetch the range of cells with data
  var lr = sheet.getLastRow();
  sheet.getRange("H2:I" + lr).clear();  
  var dataRange = sheet.getRange("B2:B" + lr);
  // Fetch values for each row in the Range.
  var data = dataRange.getValues();
  var arrayLength = data.length;

  for (var i = 0; i < arrayLength; i++) {
      try {
        //TODO: Add domain to the URL for JIRA query OR update URL for other ticket management tool
        var jiraURL = '/rest/api/2/search?jql=description%20~%20' + data[i] + '%20AND%20status%20not%20in%20(Cancel,Cancelled,Rejected,Draft,Done)%20AND%20issuetype!=sub-task%20AND%20resolution=Unresolved%20Order%20By%20created%20asc&maxResults=1&fields=key';
        var headers = {
          "Authorization": "Basic " + PropertiesService.getScriptProperties().getProperty('jiraAuth')
        };
        var params = {
          "headers": headers,
          'muteHttpExceptions': true
        };
        var response = UrlFetchApp.fetch(jiraURL, params);
        var jiraInfo = JSON.parse(response.getContentText());
        Logger.log(jiraInfo.issues.length);
        if (jiraInfo.issues.length > 0) {
          Logger.log(jiraInfo.issues[0].key);
          sheet.getRange("H" + (i+2)).setValue("/browse/" + jiraInfo.issues[0].key);
          SpreadsheetApp.flush();
        } else {
          sheet.getRange("H" + (i+2)).setValue('');
          SpreadsheetApp.flush();
        }
      } catch (e) {
        // modify cell to record error
        Logger.log(e);
      }
  }//endfor


}

function createNewSmokeBatch(id,formatDt) {
  
  var ss = SpreadsheetApp.getActiveSpreadsheet();
  var sourceSheetName = SpreadsheetApp.getActiveSheet().getName();
  var lr=ss.getLastRow();
  var sRange = ss.getRange("A2:I"+lr);
  
  var short = sourceSheetName.split("_",2);
  var dest = String(short[0])+"_"+String(short[1])+"_raw";
  PropertiesService.getScriptProperties().setProperty('target', dest);
  
  
  var data=sRange.getValues();
  var postData=new Array(); 
  
 for (var i=0;i<data.length;i++)
  {
   
 var innerPostData=new Array();
    if(data[i][4]=='Y')
    {
      innerPostData.push(id);
      innerPostData.push(formatDt);
      innerPostData.push(data[i][0]);
      innerPostData.push(data[i][1]);
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push('No Run');
      innerPostData.push('');
      innerPostData.push('');
      innerPostData.push(data[i][7]);      
  postData.push(innerPostData);
    }
  

 }
    var len=postData.length;    
    var destinationSheetName=ss.getSheetByName(PropertiesService.getScriptProperties().getProperty('target'));
    var dlr=destinationSheetName.getLastRow()+1;
    var dsr=dlr+len-1;
    var destinationRange=destinationSheetName.getRange("A"+dlr+":K"+dsr);
  
  destinationRange.setValues(postData); 
};


function Test_Execution() {
    var ui = SpreadsheetApp.getUi();
    ui.alert('Started Test Execution');
    //var subject = "Sending emails from a Spreadsheet";
    var sheet = SpreadsheetApp.getActiveSheet();
    // Fetch the range of cells with JIRA tickets
    var lr=sheet.getLastRow();
    var range = sheet.getRange("J1:J3");
  
  
  // Process the user's response.
 
    // loop through all the data
    obj.forEach(function(row, rowIdx){
      // only update status when JIRA Ticket is present
      if (row.Test_Case_Name !== ''){
        try {
          // TODO: Add testing tool URL
            var testURL='/api/submit';
                var headers = {
                    'Content-Type': 'application/json',
                    'Cache-control': 'no-cache',
                    'Authorization': 'Bearer ',
                };
            var params = {
              "method": "POST",
              "headers":headers,
              "payload":"payload",
              'muteHttpExceptions': true
            };
          
          var response = UrlFetchApp.fetch(testURL, params);
          var jiraInfo = JSON.parse(response.getContentText());
          Logger.log(jiraInfo);
          data[rowIdx][jira_ticket_col] = "/browse/"+jiraInfo.issues[0].key;
          SpreadsheetApp.flush();
        } catch(e) {
          // modify cell to record error
          Logger.log(e.message);
        }
      }
   
    });
}

