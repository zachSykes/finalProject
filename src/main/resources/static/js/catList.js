/**
 * 
 */
//$(function(){
// NOTE ilker above is shorthand for below line
$(document).ready(function(){
	// create new user or edit one
	$('.nBtn, .table .eBtn').on('click', function(event){
		//console.log("Zach -->");	// NOTE ilker, this is JS way to 
//		debugger;	// NOTE ilker, this is JS way of putting breakpoint hard coded into your code.If you uncomment this, this JS code will stop at this line for you to continue in debugger of DevTool of browser

		// NOTE ilker if you have href="" in anchor tag this jQuery marker triggers on, then you need to have below line to be able to see modal popup
		//         or if you have th:href="@{detail/(studentId=${student.studentId})}", then you need below line to stop it from showing the result as json object
		event.preventDefault();	
		var href = $(this).attr('href');
		var text = $(this).text();
		if(text=='Edit') {
			// NOTE ilker, make ajax (REST) call via jQuery's get. Then once REST service returns data (in data4cat variable) back, below function executes "asynchronously"
			$.get(href, function(data4cat, status){
				$('.myForm #petId').val(data4cat.petId);
				$('.myForm #name').val(data4cat.name);
				$('.myForm #type').val(data4cat.type);
				$('.myForm #ownerName').val(data4cat.ownerName);
				$('.myForm #address').val(data4cat.address);
				$('.myForm #age').val(data4cat.age);
				$('.myForm #isSpayed').val(data4cat.isSpayed);
				$('.myForm #birthdate').val(data4cat.birthdate);
			});
			// NOTE ilker, let Bootstrap modal popup via below jQuery call
			$('.myForm #exampleModal').modal();	// modal(), modal('show'), modal('toggle')
		} else {
			// NOTE ilker, hide studentId input div. And disable studentId input. Since studentId is "@GeneratedValue(strategy=GenerationType.AUTO)" in StudentEntity and inputs that can not be parsed to Integer will cause 400 Bad Request upon POST of form, no need for user to be able to enter anything here
			$('.myForm #petId').prop("disabled", true); // example of setting an attribute of the element
			$('.myForm #petId').prop("type", "hidden"); // NOTE this does not hide the label, just hides the input element, so below line is better
			$('.myForm #petId').parent().hide();	// NOTE ilker using jQuery APIs parent() to get parent div of #studentId input and then hide() to hide that div
			// initialize the fields of modal
			$('.myForm #petId').val('');	// NOTE ilker for post form submission to be auto serialized to StudentEntity at StudentController's method, this needs to be initialized to a value that can be parsed to Integer since studentId attribute is an Integer. Otherwise you will get 400 Bad Request upon POST submit
			$('.myForm #name').val('');
			$('.myForm #type').val('');
			$('.myForm #ownerName').val('');
			$('.myForm #address').val('');
			$('.myForm #age').val('');
			$('.myForm #isSpayed').val('');
			$('.myForm #birthdate').val('');
			// let Bootstrap modal popup via below jQuery call
			$('.myForm #exampleModal').modal();	// modal(), modal('show'), modal('toggle')			
		}
	});
	
	$('.table .dBtn').on('click', function(event){
		event.preventDefault();	
		var href = $(this).attr('href');
		$('#exampleModal4delete #dConfBtn').attr('href', href);
		// let Bootstrap modal popup via below jQuery call
		$('#exampleModal4delete').modal();	// modal(), modal('show'), modal('toggle')			
		

	});
		
});