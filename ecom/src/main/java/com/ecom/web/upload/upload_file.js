$(function() {
	$(":file").change(addSelectedValue);
	$(":img").click(removeSelectedValue);

});

function addSelectedValue() {
	var index = $(":file").index(this);
	var index_id = index + 1;
	var value = $(this).val();
	var spanId = "#" + index_id;
	var aRefId = "#aref_" + index_id;
	var methodCall = ' onClick=removeSelectedValue(' + index_id + ');';
	
	var tag = '<img id=' + aRefId + ' src="images/buttons/cross.png"'
	+ methodCall + ' style="width:16px; height:16px;"' + ' />';
	
	$(spanId).text(value).append(tag);
}

function removeSelectedValue(index) {
	var spanId = "#" + index;
	
	$(":file").eq(index - 1).val("");
	$(spanId).text("");
}
