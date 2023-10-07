function today(){
    var currentDate = new Date();

    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');

    return year + '.' + month + '.' + day;
}

function yesterday(){
    var today = new Date();
    var currentDate = new Date(today.setDate(today.getDate() - 1));

    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');

    return year + "." + month + "." + day;
}

function feedbackUpdate(createDate){
    var date = createDate;
    var textareaValue = document.querySelector('.feedback-textarea').value;
    feedbackUpdateAjax(date, textareaValue);
}

function feedbackInsertAjax(){
    var textareaValue = document.querySelector('.feedback-textarea').value;
    feedInsert(textareaValue);
}