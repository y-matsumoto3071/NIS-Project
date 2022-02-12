const form = document.forms.clientEditForm;
const clientName = document.getElementById("clientName");

// 未入力チェック、未入力がなければサブミット
function emptyCheck() {
	console.log('未入力チェック開始');
    submitOK = false;
    // ***である場合
	submitOK = clientName.value != "";

    if (submitOK) {
        form.submit();
		console.log('未入力チェック完了：未入力項目なし');
        return;
    } else {
		console.log('未入力チェック完了：未入力項目あり');
	    alert("名前を入力してください。");
	}
}

