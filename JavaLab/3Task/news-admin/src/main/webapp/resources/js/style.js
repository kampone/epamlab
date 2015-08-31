
$(document).ready(function  multisel() {
			$('#select-author').multiselect();
			$('#select-tag').multiselect();
		})(jQuery);
		
		$(function date() {
			$("#datepicker").datepicker();
		});

	
	function toggleClass(el){
	if(el.className == ""){
		el.className = "active";
	} else {
		el.className = "";
	}
}