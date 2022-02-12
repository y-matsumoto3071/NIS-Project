const form = document.forms.deleteClient;
const selectCheck = document.deleteClient.selectCheck;

// 未入力チェック、未入力がなければサブミット
function emptyCheck() {
	console.log('未入力チェック開始');
    console.table(selectCheck);
    submitOK = false;

    for (let i = 0; i < selectCheck.length; i++) {
		console.log('selectCheck[i].checked:' + selectCheck[i].checked);

        // i番目のチェックボックスがチェックされているかを判定
        if (selectCheck[i].checked) {
          submitOK = true;
          break;
        }
    }

    if (submitOK) {
		if(window.confirm('削除します。よろしいですか？')){
	        form.submit();
			console.log('未入力チェック完了：未入力項目なし');
	    }
        return;
	}
	console.log('未入力チェック完了：未入力項目あり');
    alert("削除する顧客を選択してください。");
	
}

