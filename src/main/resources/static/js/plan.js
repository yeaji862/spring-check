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

function habitOn(seq){
    var date = document.querySelector('.todayDate').value;
    alert(date);
    habitOnAjax(seq, date);
    scheduleChangeOn(seq, 'habit');
}

function habitOff(seq){
    var date = document.querySelector('.todayDate').value;
    habitOffAjax(seq, date);
    scheduleChangeOff(seq, 'habit');
}

function dailyOn(seq){
    dailyOnAjax(seq);
    scheduleChangeOn(seq, 'daily');
}

function dailyOff(seq){
    dailyOffAjax(seq);
    scheduleChangeOff(seq, 'daily');
}

function scheduleChangeOn(seq, schedule){
    var date = document.querySelector('.todayDate').value;
    var animatedText = document.getElementById(schedule + seq);
    var checkOnDiv = document.querySelector('.'+ schedule+'CheckOn');
    var checkbox = document.getElementById(seq + 'checkbox-' + schedule);
    checkbox.checked = true;
    checkOnDiv.appendChild(animatedText);
    animatedText.querySelector('p').classList.add('checkOn');
    document.getElementById(schedule+seq).setAttribute('onclick', schedule + 'Off('+seq+')');
}

function scheduleChangeOff(seq, schedule){
    var date = document.querySelector('.todayDate').value;
    var animatedText = document.getElementById(schedule + seq);
    var checkOffDiv = document.querySelector('.'+ schedule+'Position' + seq);
    var checkbox = document.getElementById(seq + 'checkbox-' + schedule);
    checkbox.checked = false;
    checkOffDiv.appendChild(animatedText);
    animatedText.querySelector('p').classList.remove('checkOn');
    document.getElementById(schedule+seq).setAttribute('onclick', schedule + 'On('+seq+')');
}