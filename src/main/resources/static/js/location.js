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

function refresh(date){
    window.location.href="/check/view/refresh?date=" + date;
}

function todayLink(){
    var currentDate = today();
    window.location.href="/check/view?date=" + currentDate;
}

function planLink(date){
    if(date == null){
        var currentDate = today();
    }else{
        var parts = date.split('.');

        if (parts.length === 3) {
        var year = parts[0];
        var month = parts[1];
        var day = parts[2];

        if (day.length === 1) {
            day = "0" + day;
        }
        var currentDate = year + "." + month + "." + day;
      }
    }
    window.location.href="/check/view?date=" + currentDate;
}

function habitLink(date){
    window.location.href="/check/view/habit?date=" + date;
}

function historyLink(date){
    var currentDate = yesterday(date);
    window.location.href="/check/view/history?date=" + currentDate;
}

function infoMobile(date){
    window.location.href="/check/view/infoMobile?date=" + date;
}

function historyHabitOn(seq, date){
    window.location.href="/check/view/habit/"+seq+"?division=on&&date=" + date;
}

function historyHabitOff(seq, date){
    window.location.href="/check/view/habit/"+seq+"?division=off&&date=" + date;
}

function historyDailyOn(seq, date){
    window.location.href="/check/view/daily/"+seq+"?division=on&&date=" + date;
}

function historyDailyOff(seq, date){
    window.location.href="/check/view/daily/"+seq+"?division=off&&date=" + date;
}

