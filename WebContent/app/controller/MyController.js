Ext.define('findme.controller.MyController', {
	extend : 'Ext.app.Controller',

	init : function() {
		this.control({
			'loginview' : {
				afterrender : function() {
					Ext.get('registernow').on('click', function() {
						registernow();
					});
				}
			},
			'loginview button[id=loginnow]' : {
				loginin : this.loginuser
			},
			'registrationview button[id=submitregistration]' : {
				registerin : this.registeruser
			},
			'detailview checkboxfield[id=chkbx]' : {
				chkbxin : this.chkbxin
			},
			'detailview button[id=save]' : {
				savein : this.dataValidation
			},
			'detailview button[id=refresh]' : {
				refreshin : this.load
			},
			'detailview' : {
				afterrender : function() {
					Ext.get('logout').on('click', function() {
						logout();
					});
				}
			}
		});
	},

	chkbxin : function() {
		if (isAddressEntered()) {
			copyAddress();
		} else {
			Ext.getCmp('chkbx').setValue(false);
			Ext.Msg.alert('Error', 'Please enter the present address.');
		}
	},

	loginuser : function(username, password) {
		if (username != '' && password != '') {
			login(username, password);
		} else {
			resetLoginForm();
		}
	},

	load : function() {
		Ext.Ajax
				.request({
					url : 'refresh.action',
					method : 'POST',
					headers : {
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					jsonData : JSON.stringify({
						"username" : Ext.getCmp('user').getValue()
					}),
					success : function(response) {
						var result = Ext.decode(response.responseText);
						if (result.msg) {
							refresh(result);
						} else {
							Ext.Msg.alert('Staus', 'Refresh Issue.');
						}
					},
					failure : function(response) {
						alert('server-side failure with status code '
								+ response.status);
					}
				});
	},

	registeruser : function(username, password, email) {
		Ext.Ajax
				.request({
					url : 'register.action',
					method : 'POST',
					headers : {
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					jsonData : JSON.stringify({
						"username" : username,
						"password" : password,
						"email" : email
					}),
					success : function(response) {
						var result = Ext.decode(response.responseText);
						if (result) {
							logout();
						} else {
							resetRegistrationForm();
						}
					},
					failure : function(response) {
						alert('server-side failure with status code '
								+ response.status);
					}
				});
	},

	dataValidation : function() {
		var d = new Date(Ext.getCmp('dob').getValue());
		var curr_date = d.getDate();
		var curr_month = d.getMonth() + 1; // Months are zero based
		var curr_year = d.getFullYear();
		var person = {
			"firstname" : Ext.getCmp('firstname').getValue(),
			"middlename" : Ext.getCmp('middlename').getValue(),
			"lastname" : Ext.getCmp('lastname').getValue(),
			"dob" : curr_year + "/" + curr_month + "/" + curr_date,
			"fmhname" : Ext.getCmp('fmhname').getValue(),
			"gender" : Ext.getCmp('gender').getChecked()[0].boxLabel,
			"marital" : Ext.getCmp('marital').getValue()
		};
		var address = {
			"address" : Ext.getCmp('presentaddr').getValue(),
			"addrSeq" : Ext.getCmp('addrseq').getValue(),
			"state" : Ext.getCmp('state').getValue(),
			"district" : Ext.getCmp('district').getValue(),
			"pincode" : Ext.getCmp('pincode').getValue(),
			"phonenumber" : Ext.getCmp('phonenumber').getValue().toString(),
			"mobilenumber" : Ext.getCmp('mobilenumber').getValue().toString(),
			"addrind" : "N",
		};
		var address1 = {
			"address" : Ext.getCmp('permanentaddr').getValue(),
			"addrSeq" : Ext.getCmp('addrseq1').getValue(),
			"state" : Ext.getCmp('state1').getValue(),
			"district" : Ext.getCmp('district1').getValue(),
			"pincode" : Ext.getCmp('pincode1').getValue(),
			"phonenumber" : Ext.getCmp('phonenumber1').getValue().toString(),
			"mobilenumber" : "(null)",
			"addrind" : Ext.getCmp('chkbx').getValue(),
		};
		var project = {
			"project" : Ext.getCmp('currentprojectname').getValue(),
			"designation" : Ext.getCmp('designation').getValue(),
			"role" : Ext.getCmp('role').getValue(),
			"projectdesc" : Ext.getCmp('currentprojdesc').getValue(),
		};
		Ext.Ajax
				.request({
					url : 'save.action',
					method : 'POST',
					headers : {
						'Content-Type' : 'application/json'
					},
					dataType : 'json',
					jsonData : JSON.stringify({
						"email" : Ext.getCmp('email').getValue(),
						"person" : person,
						"address" : address,
						"permanentaddr" : address1,
						"project" : project
					}),
					success : function(response) {
						var result = Ext.decode(response.responseText);
						if (result) {
							Ext.Msg.alert('Staus', 'Successfully saved.');
						} else {
							Ext.Msg.alert('Staus', 'Not saved.');
						}
					},
					failure : function(response) {
						alert('server-side failure with status code '
								+ response.status);
					}
				});
	}

});

function login(username, password) {
	Ext.Ajax.request({
		url : 'login.action',
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json'
		},
		dataType : 'json',
		jsonData : JSON.stringify({
			"username" : username,
			"password" : password
		}),
		success : function(response) {
			var result = Ext.decode(response.responseText);
			if (result.msg) {
				userdetail(result);
			} else {
				resetLoginForm();
			}
		},
		failure : function(response) {
			alert('server-side failure with status code ' + response.status);
		}
	});
}

function resetLoginForm() {
	Ext.Msg.alert('Error', 'Please Enter the Valid Username and Password.');
	Ext.getCmp('username').setValue('');
	Ext.getCmp('password').setValue('');
}

function resetRegistrationForm() {
	Ext.Msg.alert('Error',
			'Username already exist. Please enter the Valid User.');
	Ext.getCmp('user').setValue('');
	Ext.getCmp('email').setValue('');
	Ext.getCmp('password1').setValue('');
	Ext.getCmp('password2').setValue('');
}

function userdetail(result) {
	Ext.create('Ext.container.Viewport', {
		items : [ {
			xtype : 'detailview'
		} ]
	});
	refresh(result);
}

function refresh(result) {
	if (result.person != null) {
		Ext.getCmp('firstname').setValue(result.person.firstname);
		if (result.person.middlename == "(null)") {
			Ext.getCmp('middlename').setValue("");
		} else {
			Ext.getCmp('middlename').setValue(result.person.middlename);
		}
		var d = new Date();
		d.setTime(result.person.dob);
		Ext.getCmp('lastname').setValue(result.person.lastname);
		// Ext.getCmp('dob').setValue(d);
		Ext.getCmp('dob').setValue(Ext.Date.format(d, 'Y/m/d'));
		Ext.getCmp('fmhname').setValue(result.person.fmhname);
		if (result.person.gender == "Male") {
			Ext.getCmp('gender').items.items[0].setValue(true);
		} else {
			Ext.getCmp('gender').items.items[1].setValue(true);
		}
		Ext.getCmp('marital').setValue(result.person.marital);
	}
	if (result.user != null) {
		Ext.getCmp('user').setValue(result.user.username);
		Ext.getCmp('email').setValue(result.user.email);
	}
	if (result.address != null) {
		Ext.getCmp('presentaddr').setValue(result.address.address);
		Ext.getCmp('state').setValue(result.address.state);
		Ext.getCmp('district').setValue(result.address.district);
		Ext.getCmp('pincode').setValue(result.address.pincode);
		Ext.getCmp('phonenumber').setValue(result.address.phonenumber);
		Ext.getCmp('mobilenumber').setValue(result.address.mobilenumber);
	}
	if (result.permanentaddr != null) {
		Ext.getCmp('permanentaddr').setValue(result.permanentaddr.address);
		Ext.getCmp('state1').setValue(result.permanentaddr.state);
		Ext.getCmp('district1').setValue(result.permanentaddr.district);
		Ext.getCmp('pincode1').setValue(result.permanentaddr.pincode);
		Ext.getCmp('phonenumber1').setValue(result.permanentaddr.phonenumber);
		Ext.getCmp('chkbx').setValue(result.permanentaddr.addrind);
	}
	if (result.project != null) {
		Ext.getCmp('currentprojectname').setValue(result.project.project);
		Ext.getCmp('designation').setValue(result.project.designation);
		Ext.getCmp('role').setValue(result.project.role);
		Ext.getCmp('currentprojdesc').setValue(result.project.projectdesc);
	}
}

function registernow() {
	Ext.create('Ext.container.Viewport', {
		items : [ {
			xtype : 'registrationview'
		} ]
	});
}

function isAddressEntered() {
	if (Ext.getCmp('presentaddr').getValue() && Ext.getCmp('state').getValue()
			&& Ext.getCmp('district').getValue()
			&& Ext.getCmp('pincode').getValue()
			&& Ext.getCmp('phonenumber').getValue()) {
		return true;
	}
	return false;
}

function copyAddress() {
	if (Ext.getCmp('chkbx').getValue()) {
		Ext.getCmp('permanentaddr').setValue(
				Ext.getCmp('presentaddr').getValue());
		Ext.getCmp('state1').setValue(Ext.getCmp('state').getValue());
		Ext.getCmp('district1').setValue(Ext.getCmp('district').getValue());
		Ext.getCmp('pincode1').setValue(Ext.getCmp('pincode').getValue());
		Ext.getCmp('phonenumber1').setValue(
				Ext.getCmp('phonenumber').getValue());
	} else {
		Ext.getCmp('permanentaddr').setValue('');
		Ext.getCmp('state1').setValue('');
		Ext.getCmp('district1').setValue('');
		Ext.getCmp('pincode1').setValue('');
		Ext.getCmp('phonenumber1').setValue('');
	}
}

function logout() {
	window.location.href = "/findme";
}