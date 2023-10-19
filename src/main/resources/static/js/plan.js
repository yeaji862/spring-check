function checkParameter() {
        const urlParams = new URL(location.href).searchParams;
        const paramValue = urlParams.get('dateEx');
        if (paramValue === "0") {
            alert('유효하지 않은 날짜 형식입니다.')
        }
}

     window.onload = checkParameter;

function today(){
    var currentDate = new Date();

    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');

    return year + '.' + month + '.' + day;
}

function yesterday(date){
    var formattedDate = date.replace(/\./g, '/');
    var today = new Date(formattedDate);
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

function feedbackInsert(){
    var textareaValue = document.querySelector('.feedback-textarea').value;
    feedbackInsertAjax(textareaValue);
}

function habitOn(seq){
    var date = document.querySelector('.todayDate').value;
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
    document.querySelector('.'+schedule+'Div'+seq).setAttribute('onclick', schedule + 'Off('+seq+')');
    checkOnDiv.classList.contains("history-date");
}

function scheduleChangeOff(seq, schedule){
alert('.'+ schedule+'Position' + seq);
    var date = document.querySelector('.todayDate').value;
    var animatedText = document.getElementById(schedule + seq);
    var checkOffDiv = document.querySelector('.'+ schedule+'Position' + seq);
    var checkbox = document.getElementById(seq + 'checkbox-' + schedule);
    checkbox.checked = false;
    checkOffDiv.appendChild(animatedText);
    animatedText.querySelector('p').classList.remove('checkOn');
    document.querySelector('.'+schedule+'Div'+seq).setAttribute('onclick', schedule + 'On('+seq+')');
    checkOffDiv.classList.contains("history-date");
}

function dailyDelete(seq){
    dailyContentDelete(seq, function (result) {
        if (result != null) {
            document.getElementById('daily' + seq).remove();
            document.querySelector('.dailyPosition' + seq).remove();
        }
    });
}

function habitDelete(seq){
    habitContentDelete(seq, function (result) {
        if (result != null) {
            document.getElementById('habit' + seq).remove();
            document.querySelector('.habitPosition' + seq).remove();
        }
    });
}

function editBtn(){
    var history = document.querySelectorAll('.history-date');
    var modify = document.querySelectorAll('.modify-btn');
    var modifyInput = document.querySelectorAll('.modify-input')
    var contentP = document.querySelectorAll('.contentP');
    var deleteBtn = document.querySelectorAll('.delete-btn');
    document.querySelector('.editButton').textContent = '완료';
    document.querySelector('.editButton').setAttribute('onclick', 'modifyDone()');

         modify.forEach(element => {
            element.classList.remove('none-btn');
          });
         modifyInput.forEach(element => {
            element.classList.remove('none-btn');
          });
         contentP.forEach(element => {
            element.style.display = 'none';
          });
         deleteBtn.forEach(element => {
            element.classList.remove('none-btn');
          });
         history.forEach(element => {
            element.style.display = ('none');
          });
}

function modifyDone(){
    var history = document.querySelectorAll('.history-date');
    var modify = document.querySelectorAll('.modify-btn');
    var modifyInput = document.querySelectorAll('.modify-input')
    var contentP = document.querySelectorAll('.contentP');
    var deleteBtn = document.querySelectorAll('.delete-btn');
    document.querySelector('.editButton').textContent = '편집';
    document.querySelector('.editButton').setAttribute('onclick', 'editBtn()');

         modify.forEach(element => {
            element.classList.add('none-btn');
          });
         modifyInput.forEach(element => {
            element.classList.add('none-btn');
          });
         contentP.forEach(element => {
            element.style.display = 'block';
          });
         deleteBtn.forEach(element => {
            element.classList.add('none-btn');
          });
         history.forEach(element => {
            element.style.display = ('inline-block');
          });
}

function dailyModify(seq){
    var content = document.querySelector('.daily-input' + seq).value;
     dailyContentEdit(seq, content, function (result) {
            if (result) {
                document.querySelector('.dailyP'+ seq).textContent = content;
                alert('수정이 완료되었습니다!');
            }
        });
}

function habitModify(seq){
    var content = document.querySelector('.habit-input' + seq).value;
     habitContentEdit(seq, content, function (result) {
            if (result) {
                document.querySelector('.habitP'+ seq).textContent = content;
                alert('수정이 완료되었습니다!');
            }
        });
}

function beforeDate(dateString){
    var date = subMonth(dateString);

    window.location.href="/check/view?date=" + date;
}

function afterDate(dateString){
    var date = addMonth(dateString);

    window.location.href="/check/view?date=" + date;
}

function mobileBeforeDate(dateString){
    var date = subMonth(dateString);

    window.location.href="/check/view/infoMobile/?date=" + date;
}

function mobileAfterDate(dateString){
    var date = addMonth(dateString);

    window.location.href="/check/view/infoMobile/?date=" + date;
}

function subMonth(dateString){
  var formattedDate = dateString.replace(/\./g, '/');
  var date = new Date(formattedDate+'/01');
  date.setMonth(date.getMonth() - 1);

  var year = date.getFullYear();
  var month = (date.getMonth() + 1).toString().padStart(2, '0');

  return year + '.' + month + '.01'
}

function addMonth(dateString){
 var formattedDate = dateString.replace(/\./g, '/');
  var date = new Date(formattedDate+'/01');

  date.setMonth(date.getMonth() + 1);

  var year = date.getFullYear();
  var month = (date.getMonth() + 1).toString().padStart(2, '0');

  return year + '.' + month + '.01'
}