function oauth_google(){
    window.location.href="/oauth2/login?value=google";
}

function oauth_kakao(){
    window.location.href="/oauth2/login?value=kakao";
}

function oauth_naver(){
    window.location.href="/oauth2/login?value=naver";
}

function signUp(){
    window.location.href="/user/signUp";
}

function findPass(){
    window.location.href="/user/findPass";
}

function refresh(){
    var currentDate = today();
    window.location.href="/check/refresh/?date=" + currentDate;
}

function planLink(){
    var currentDate = today();
    window.location.href="/check?date=" + currentDate;
}

function habitLink(){
    var currentDate = today();
    window.location.href="/check/habit/?date=" + currentDate;
}

function historyLink(){
    var currentDate = yesterday();
    window.location.href="/check/history/?date=" + currentDate;
}

function infoMobile(){
    var currentDate = yesterday();
    window.location.href="/check/infoMobile/?date=" + currentDate;
}

