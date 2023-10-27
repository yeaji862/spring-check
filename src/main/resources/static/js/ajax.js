function findId(email, callback) {
  $.ajax({
    url: "/user/" + email,
    method: "GET",
    dataType: "json",
    success: function (data) {
      if (data === 0) {
        callback(false);
      } else {
        callback(true);
      }
    },
    error: function () {
      alert('다시 시도해주세요');
    }
  });
}

function sandMail(email, callback) {
  $.ajax({
    url: "/edit",
    method: "POST",
    data: {"userMail" : email},
    dataType: "text",
    success: function (data) {
      if (data === 'ok') {
        callback(true);
      } else {
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function feedbackUpdateAjax(createDate, textareaValue){
  $.ajax({
    url: "/check/rest/feedback",
    method: "POST",
    data: {"createDate" : createDate , "division" : "edit", "content" : textareaValue},
    dataType: "text",
    success: function (data) {
      if (data < 0) {
              alert('다시 시도해주세요!');
      }else{
        alert('수정이 완료되었습니다!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function feedbackInsertAjax(textareaValue){
  $.ajax({
    url: "/check/rest/feedback",
    method: "POST",
    data: {"createDate" : null , "division" : "upload", "content" : textareaValue},
    dataType: "text",
    success: function (data) {
      if (data < 0) {
        alert('다시 시도해주세요!');
      }else{
               alert('수정이 완료되었습니다!');
             }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function habitOnAjax(seq, date){
  $.ajax({
    url: "/check/rest/habit",
    method: "POST",
    data: {"division" : "on", "seq" : seq, "date" : date},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
           console.log('ok');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function habitOffAjax(seq, date){
  $.ajax({
    url: "/check/rest/habit",
    method: "POST",
    data: {"division" : "off", "seq" : seq, "date" : date},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
      console.log('ok');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function dailyOnAjax(seq){
  $.ajax({
    url: "/check/rest/daily",
    method: "POST",
    data: {"division" : "on", "seq" : seq},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        console.log('ok');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function dailyOffAjax(seq){
  $.ajax({
    url: "/check/rest/daily",
    method: "POST",
    data: {"division" : "off", "seq" : seq},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        console.log('ok');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function dailyUpload(content, date, callback){
  $.ajax({
    url: "/check/rest/daily/status",
    method: "POST",
    data: {"division" : "upload", "content" : content, "date" : date},
    dataType: "text",
    success: function (data) {
        if (data > 0) {
            callback(data);
        }else{
            alert('다시 시도해주세요!');
        }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function habitUpload(content, date,callback){
  $.ajax({
    url: "/check/rest/habit/status",
    method: "POST",
    data: {"division" : "upload", "content" : content, "date" : date},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(data);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function dailyContentDelete(seq, callback){
  $.ajax({
    url: "/check/rest/daily/status",
    method: "POST",
    data: {"division" : "delete", "seq" : seq},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(data);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function habitContentDelete(seq, callback){
  $.ajax({
    url: "/check/rest/habit/status",
    method: "POST",
    data: {"division" : "delete", "seq" : seq},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(data);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function dailyContentEdit(seq, content, callback){
  $.ajax({
    url: "/check/rest/daily/status",
    method: "POST",
    data: {"division" : "edit", "seq" : seq, "content" : content},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(true);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function habitContentEdit(seq, content, callback){
  $.ajax({
    url: "/check/rest/habit/status",
    method: "POST",
    data: {"division" : "edit", "seq" : seq, "content" : content},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(true);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}

function imgEdit(seq, content, callback){
  $.ajax({
    url: "/check/rest/habit/status",
    method: "POST",
    data: {"division" : "edit", "seq" : seq, "content" : content},
    dataType: "text",
    success: function (data) {
      if (data > 0) {
        callback(true);
      }else{
        alert('다시 시도해주세요!');
      }
    },
    error: function (request, status, error) {
      alert('다시 시도해주세요!');
    }
  });
}