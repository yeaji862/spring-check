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