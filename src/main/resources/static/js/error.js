function mainPage(){
    var currentDate = new Date();

    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');

    window.location.href="/check/view?date=" + year + '.' + month + '.' + day;
}