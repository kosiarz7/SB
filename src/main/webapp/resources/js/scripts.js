/**
 * Blokuje stronę poprzez pokazania ajaxowej kurtyny.
 */
function showCurtain() {
	$('#curtain').css("display", "block");
	if ("0px" == $('#curtainContent').css("margin-top")) {
		var contentWidth = $('#curtainContent').outerWidth(true);
		var contentHeight = $('#curtainContent').outerHeight(true);
		$('#curtainContent').css("margin-top", ($('#curtain').outerHeight(true) - contentHeight) / 2 +  "px");
		$('#curtainContent').css("margin-left", ($('#curtain').outerWidth(true) - contentWidth) / 2 +  "px");
	}
}

/**
 * Odblokowuje stronę poprzez zdjęcie ajaxowej kurtyny.
 */
function hideCurtain() {
	$('#curtain').css("display", "none");
}
