let mostSearchedDocumentInfoHashMap;
let mostSearchedTextHashMap;
let frustrationLevelHashMap;
let mostAnsweredResponseHashMap;
let listWatsonAssistantConversationalLog;
let data;

$(document).ready(function() {
    google.charts.load('current', {
        packages : [ 'corechart', 'bar','table' ]
    });
});

function returnGoogleData(finalHashMap){
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Text');
    data.addColumn('number', 'Quantity');
    Object.keys(finalHashMap).forEach(function(key) {
        data.addRow([ key, finalHashMap[key] ]);
    });
    return data;
}

function openInsight(evt, insightName) {
    let i, tabContent, tabLinks;
    tabContent = document.getElementsByClassName("group");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }
    tabLinks = document.getElementsByClassName("tabLinks");
    for (i = 0; i < tabLinks.length; i++) {
        tabLinks[i].className = tabLinks[i].className.replace(" active", "");
    }
    document.getElementById(insightName).style.display = "flex";
    evt.currentTarget.className += " active";
}

function getMostSearchedDocumentInfo() {

    const fromDate = document.getElementById('mostSearchedDocumentInfoFromDate').value;
    const toDate = document.getElementById('mostSearchedDocumentInfoToDate').value;

    console.log(fromDate)
    console.log(toDate)

    $.ajax({
        url :"/InsightsService/getMostSearchedDocumentInfo",
        method: "POST",
        data: JSON.stringify({ fromDate: fromDate, toDate: toDate, typeResponse: null, queryText: null }),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            console.log('success', data);
            console.log('Name', data.dynamicInsight.name);
            console.log('Value', data.dynamicInsight.value);
            document.getElementById('mostSearchedDocumentInfoName').textContent = data.dynamicInsight.name;
            document.getElementById('mostSearchedDocumentInfoValue').textContent  = data.dynamicInsight.value;
            mostSearchedDocumentInfoHashMap = data.dynamicInsight.finalHashMap;
            console.log(data.dynamicInsight.finalHashMap)
            data = returnGoogleData(mostSearchedDocumentInfoHashMap);
            const options = {
                is3D: true,
                enableInteractivity: true
            };
            const chart = new google.visualization.PieChart(document.getElementById('mostSearchedDocumentInfoChart'));
            chart.draw(data, options);
        },
        error: function(exception){alert('Exception:'+exception);}
    });
}

function getMostSearchedText() {

    const fromDate = document.getElementById('mostSearchedTextFromDate').value;
    const toDate = document.getElementById('mostSearchedTextToDate').value;

    console.log(fromDate)
    console.log(toDate)

    $.ajax({
        url :"/InsightsService/getMostSearchedText",
        method: "POST",
        data: JSON.stringify({ fromDate: fromDate, toDate: toDate, typeResponse: null, queryText: null }),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            console.log('success', data);
            console.log('Name', data.dynamicInsight.name);
            console.log('Value', data.dynamicInsight.value);
            document.getElementById('mostSearchedTextName').textContent = data.dynamicInsight.name;
            document.getElementById('mostSearchedTextValue').textContent  = data.dynamicInsight.value;
            console.log(data.dynamicInsight.finalHashMap)
            mostSearchedTextHashMap = data.dynamicInsight.finalHashMap;
            console.log(mostSearchedTextHashMap)
            data = returnGoogleData(mostSearchedTextHashMap);
            const options = {
                is3D: true,
                enableInteractivity: true,
            }
            const chart = new google.visualization.PieChart(document.getElementById('mostSearchedTextChart'));
            chart.draw(data, options);
        },
        error: function(exception){alert('Exception:'+exception);}
    });

}

function getMostAnsweredResponse() {

    const fromDate = document.getElementById('mostAnsweredResponseFromDate').value;
    const toDate = document.getElementById('mostAnsweredResponseToDate').value;

    console.log(fromDate)
    console.log(toDate)

    $.ajax({
        url :"/InsightsService/getMostReturnedAnswers",
        method: "POST",
        data: JSON.stringify({ fromDate: fromDate, toDate: toDate, typeResponse: null, queryText: null }),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            console.log('success', data);
            console.log('Name', data.dynamicInsight.name);
            console.log('Value', data.dynamicInsight.value);
            document.getElementById('mostAnsweredResponseName').textContent = data.dynamicInsight.name;
            document.getElementById('mostAnsweredResponseValue').textContent  = data.dynamicInsight.value;
            console.log(data.dynamicInsight.finalHashMap)
            mostAnsweredResponseHashMap = data.dynamicInsight.finalHashMap;
            console.log(mostAnsweredResponseHashMap)
            data = returnGoogleData(mostAnsweredResponseHashMap);
            const options = {
                width: 1200,
                height: 700,
                is3D: true,
                enableInteractivity: true,
                chartArea:{left:450,top:60,width:'50%',height:'80%'},
                legend: {
                    alignment: 'center',
                },
                vAxis: {
                    textStyle: {
                        fontSize: 14,
                        bold: true,
                        color: '#848484'
                    }
                }
            }
            const chart = new google.visualization.BarChart(document.getElementById('mostAnsweredResponseChart'));
            chart.draw(data, options);
        },
        error: function(exception){alert('Exception:'+exception);}
    });
}

function getFrustrationLevel() {

    const fromDate = document.getElementById('frustrationLevelFromDate').value;
    const toDate = document.getElementById('frustrationLevelToDate').value;

    console.log(fromDate)
    console.log(toDate)

    $.ajax({
        url :"/InsightsService/getFrustrationLevel",
        method: "POST",
        data: JSON.stringify({ fromDate: fromDate, toDate: toDate, typeResponse: null, queryText: null }),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            console.log('success', data);
            console.log('Name', data.dynamicInsight.name);
            console.log('Value', data.dynamicInsight.value);
            document.getElementById('frustrationLevelName').textContent = data.dynamicInsight.name;
            document.getElementById('frustrationLevelValue').textContent  = data.dynamicInsight.value;
            console.log(data.dynamicInsight.finalHashMap)
            frustrationLevelHashMap = data.dynamicInsight.finalHashMap;
            console.log(frustrationLevelHashMap)
            data = returnGoogleData(frustrationLevelHashMap);
            const options = {
                is3D: true,
                enableInteractivity: true
            };
            const chart = new google.visualization.ColumnChart(document.getElementById('frustrationLevelChart'));
            chart.draw(data, options);
        },
        error: function(exception){alert('Exception:'+exception);}
    });
}


function drawConversationalLogTable() {

    data = new google.visualization.DataTable();

    data.addColumn('string', 'Watson Chat ID');
    data.addColumn('string', 'Request Text');
    data.addColumn('date', 'Request Date');
    data.addColumn('string', 'Response Type');
    data.addColumn('string', 'Response Text');
    data.addColumn('date', 'Response Date');
    data.addColumn('string', 'User Email');

    const fromDate = document.getElementById('conversationsLogFromDate').value;
    const toDate = document.getElementById('conversationsLogToDate').value;
    let responseType = document.getElementById('conversationsLogResponseTypeSelect').value;
    let watsonChatId = document.getElementById('conversationsLogWatsonChatIdText').value;

    console.log(fromDate)
    console.log(toDate)
    console.log(responseType)
    console.log(watsonChatId)

    if(responseType === undefined || responseType === ''){
        responseType = null;
    }

    if(watsonChatId === undefined || watsonChatId === ''){
        watsonChatId = null;
    }

    $.ajax({
        url :"/InsightsService/getConversationalLogs",
        method: "POST",
        data: JSON.stringify({ fromDate: fromDate, toDate: toDate, typeResponse: responseType, watsonChatId: watsonChatId }),
        dataType: 'json',
        contentType: "application/json",
        success: function(response) {
            console.log('success', response);
            listWatsonAssistantConversationalLog = response.dynamicInsight.finalList;
            console.log(listWatsonAssistantConversationalLog)
            listWatsonAssistantConversationalLog.forEach(function(logInd) {
                data.addRow([
                    logInd.watsonChatId,
                    logInd.requestText,
                    new Date (logInd.requestDate),
                    logInd.responseType,
                    logInd.responseText,
                    new Date (logInd.responseDate),
                    logInd.userEmail]);
            });

            var cssClassNames = {'oddTableRow': 'oddRow'};

            const options = {
                width: '100%',
                height: '100%',
                showRowNumber: true,
                allowHtml: true,
                page: 'enable',
                pageSize: 20,
                pagingSymbols: {
                    prev: 'Prev',
                    next: 'Next'
                },
                cssClassNames: cssClassNames
            };

            var formatter3 = new google.visualization.DateFormat({pattern: "EEE, MMM d, yyyy, HH:mm:ss"});
            formatter3.format(data, 2);
            formatter3.format(data, 5);
            const table = new google.visualization.Table(document.getElementById('conversationsLogChart'));
            table.draw(data, options);
        },
        error: function(exception){alert('Exception:'+exception);}
    });
}