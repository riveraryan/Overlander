/**
 * JavaScript for the Overlander site
 * Author: Ryan Rivera
 * Date: 11/20/21
 * Version: 1.0
 */

window.onload = function()
{
    getEvents();
    getUsers();
    getStatusMetrics();
    getEnvironmentMetrics();
    getHttpServerMetrics();
    getProcessUptime()
}

/**
 * Method uses API endpoint to fetch Event data from the database
 */
function getEvents()
{
    if(document.getElementById("eventsTable") != null){
        let uri = "http://localhost:8080/overlander/api/v1/events";
        let config = {
            method : "GET",
            mode : "cors",
            headers : {
                "Content-Type" : "application/json"
            }
        };
        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                renderEventsTable(json);
            });
    }
}

/**
 * Method processes given JSON Event objects to be rendered into the Events table
 * @param jsonData JSON Event objects
 */
function renderEventsTable(jsonData)
{
    let eventsTable = document.getElementById("eventsTable");
    for(let i = 0; i < jsonData.length; i++){
        eventsTable.appendChild(createEventRow(jsonData[i]));
    }
}

/**
 * Method creates table row elements to represent the given Event object
 * @param event Event object
 * @returns {HTMLTableRowElement} table row containing Event details
 */
function createEventRow(event)
{
    let row = document.createElement("tr");
    let name = document.createElement("td");
    let date = document.createElement("td");
    let city = document.createElement("td");
    let state = document.createElement("td");

    name.innerHTML = event.name;
    name.setAttribute("class", "text-left");
    date.innerHTML = event.date;
    city.innerHTML = event.city;
    state.innerHTML = event.state;

    row.appendChild(name);
    row.appendChild(city);
    row.appendChild(state);
    row.appendChild(date);

    return row;
}

/**
 * Method uses API endpoint to fetch User data from the database. JSON results are then
 * rendered to it's respective table
 */
function getUsers()
{
    if(document.getElementById("usersTable") != null) {
        let uri = "http://localhost:8080/overlander/api/v1/users";
        let config = {
            method: "GET",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        };
        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                renderUserTable(json);
            });
    }
}

/**
 * Method processes given JSON User objects to be rendered into the User table
 * @param jsonData JSON User objects
 */
function renderUserTable(jsonData)
{
    let membersTable = document.getElementById("usersTable");
    for(let i = 0; i < jsonData.length; i++){
        membersTable.appendChild(createMemberRow(jsonData[i]));
    }
}

/**
 * Method creates table row elements to represent the given User object
 * @param user User object
 * @returns {HTMLTableRowElement} table row containing User details
 */
function createMemberRow(user)
{
    let row = document.createElement("tr");
    let fName = document.createElement("td");
    let lName = document.createElement("td");
    let email = document.createElement("td");
    let phone = document.createElement("td");

    fName.innerHTML = user.fname;
    lName.innerHTML = user.lname;
    email.innerHTML = user.email;
    phone.innerHTML = user.phone;

    row.appendChild(fName);
    row.appendChild(lName);
    row.appendChild(email);
    row.appendChild(phone);

    return row;
}

/**
 * Method uses API endpoint to fetch health metrics from the Spring Actuator dependency.
 * JSON results are then rendered to it's respective table
 */
function getStatusMetrics()
{
    if(document.getElementById("statusTable") != null){
        let uri = "http://localhost:8080/actuator/health";
        let config = {
            method : "GET",
            mode : "cors",
            headers : {
                "Content-Type" : "application/json"
            }
        };

        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                createStatusDetails(json);
            });
    }
}

/**
 * Method creates table row elements to represent the given metrics data
 * @param jsonData Spring Actuator api metrics data
 */
function createStatusDetails(jsonData)
{
    let appStatus = document.getElementById("appStatus");
    appStatus.innerHTML = jsonData.status;

    let dbStatus = document.getElementById("dbStatus");
    dbStatus.innerHTML = jsonData.components.db.status;

    let pingStatus = document.getElementById("pingStatus");
    pingStatus.innerHTML = jsonData.components.ping.status;
}

/**
 * Method uses API endpoint to fetch server environment details from the Spring Actuator
 * dependency. JSON results are then rendered to it's respective table
 */
function getEnvironmentMetrics()
{
    if(document.getElementById("environmentTable") != null) {
        let uri = "http://localhost:8080/actuator/env/";
        let config = {
            method: "GET",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        };

        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                createEnvironmentDetails(json);
            });
    }
}

/**
 * Method creates table row elements to represent the given metrics data
 * @param jsonData Spring Actuator api metrics data
 */
function createEnvironmentDetails(jsonData)
{
    let os = document.getElementById("os");
    os.innerHTML = jsonData.propertySources[2].properties["os.name"].value;

    let javaVersion = document.getElementById("javaVersion");
    javaVersion.innerHTML = jsonData.propertySources[2].properties["java.vm.specification.version"].value;

    let localPort = document.getElementById("localPort");
    localPort.innerHTML = jsonData.propertySources[0].properties["local.server.port"].value;

    let country = document.getElementById("country");
    country.innerHTML = jsonData.propertySources[2].properties["user.country"].value;

    let timeZone = document.getElementById("timeZone");
    timeZone.innerHTML = jsonData.propertySources[2].properties["user.timezone"].value;
}

/**
 * Method uses API endpoint to fetch http server request metrics from the Spring Actuator
 * dependency. JSON results are then rendered to it's respective table
 */
function getHttpServerMetrics()
{
    if(document.getElementById("metricsTable") != null) {
        let uri = "http://localhost:8080/actuator/metrics/http.server.requests";
        let config = {
            method: "GET",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        };

        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                createHttpServerRequestDetails(json);
            });
    }
}

/**
 * Method creates table row elements to represent the given metrics data
 * @param jsonData Spring Actuator api metrics data
 */
function createHttpServerRequestDetails(jsonData)
{
    let requestTotal = document.getElementById("requestTotal");
    requestTotal.innerHTML = jsonData.measurements[0].value;
}

/**
 * Method uses API endpoint to fetch process Uptime metrics from the Spring Actuator dependency.
 * JSON results are then rendered to it's respective table
 */
function getProcessUptime()
{
    if(document.getElementById("metricsTable") != null) {
        let uri = "http://localhost:8080/actuator/metrics/process.uptime";
        let config = {
            method: "GET",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        };

        fetch(uri, config)
            .then((response) => {
                return response.json();
            })
            .then((json) => {
                createProcessUptimeDetails(json);
            });
    }
}

/**
 * Method creates table row elements to represent the given metrics data
 * @param jsonData Spring Actuator api metrics data
 */
function createProcessUptimeDetails(jsonData)
{
    let processUptime = document.getElementById("processUptime");
    processUptime.innerHTML = printTime(jsonData.measurements[0].value)
}

/**
 * Method converts a given number of seconds into hours, minutes, and seconds in a formatted String
 * @param totalSeconds number of seconds
 * @returns {string} String formatted to display hours, minutes, and seconds
 */
function printTime(totalSeconds)
{
    let seconds = totalSeconds % 60;
    let minutes = totalSeconds / 60 % 60;
    let hours = totalSeconds / 60 / 60 % 60;
    return Math.floor(hours) + " Hrs, " +
        Math.floor(minutes) + " Mins, " +
        Math.floor(seconds) + " Secs";
}