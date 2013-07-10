Ext.require([ 'Ext.form.*', 'Ext.tip.*' ]);
Ext.QuickTips.init();
Ext
		.define(
				'findme.view.Details',
				{
					extend : 'Ext.form.Panel',
					alias : 'widget.detailview',
					width : '98%',
					height : '100%',
					bodyStyle : 'padding:5px 5px 5px 5px',
					margin : "10 10 10 10",
					renderTo : 'find',
					monitorValid : true,
					layout : 'vbox',
					items : [
							{
								xtype : 'container',
								layout : 'hbox',
								width : '100%',
								height : '10%',
								margin : "2 0 0 0",
								items : [ , {
									xtype : 'tbspacer',
									width : '1%'
								}, {
									xtype : 'displayfield',
									fieldLabel : 'Welcome',
									name : 'user',
									id : 'user',
								}, {
									xtype : 'tbspacer',
									width : '67%'
								}, {
									xtype : 'button',
									text : 'Save',
									id : 'save',
									formBind : true,
									disabled : true,
									listeners : {
										click : function() {
											this.fireEvent('savein');
										}
									}
								}, {
									xtype : 'tbspacer',
									width : '1%'
								}, {
									xtype : 'button',
									text : 'Refresh',
									id : 'refresh',
									listeners : {
										click : function() {
											this.fireEvent('refreshin');
										}
									}
								}, {
									xtype : 'tbspacer',
									width : '1%'
								}, {
									xtype : 'button',
									text : 'Generate Pdf',
									id : 'pdf',
									listeners : {
										click : function() {
											this.fireEvent('generatein');
										}
									}
								},{
									xtype : 'tbspacer',
									width : '1%'
								}, {
									xtype : 'button',
									text : 'Logout',
									id : 'logout'
								} ]
							},
							{
								xytpe : 'panel',
								title : 'Employee Information',
								headerPosition : 'top',
								layout : 'accordion',
								frame : true,
								width : '100%',
								height : '100%',
								bodyStyle : 'padding:5px 5px 5px 5px',
								margin : "10 10 10 10",// Same as CSS ordering
								// (top, right, bottom,
								// left)
								layoutConfig : {
									// layout-specific configs go here
									animate : true,
									multi : false,
									activeOnTop : false,
								},
								items : [
										{
											frame : true,
											bodyStyle : 'padding:5px 5px 5px 5px',
											margin : "5 5 5 5",
											width : '100%',
											title : 'Employee Personal Information',
											items : [
													{
														xtype : 'textfield',
														name : 'firstname',
														id : 'firstname',
														fieldLabel : 'First Name',
														allowBlank : false,
														maxLength : 25,
														minLength : 5,
														width : '35%',
														labelWidth : 200
													},
													{
														xtype : 'textfield',
														name : 'middlename',
														id : 'middlename',
														fieldLabel : 'Middle Name',
														allowBlank : true,
														maxLength : 25,
														width : '35%',
														labelWidth : 200
													},
													{
														xtype : 'textfield',
														name : 'lastname',
														id : 'lastname',
														fieldLabel : 'Last Name',
														allowBlank : false,
														maxLength : 25,
														minLength : 5,
														width : '35%',
														labelWidth : 200
													},
													{
														xtype : 'datefield',
														name : 'dob',
														id : 'dob',
														fieldLabel : 'Date of Birth',
														allowBlank : false,
														width : '35%',
														labelWidth : 200,
														format : 'Y/m/d',
														altFormats : 'Y/m/d',
														maxValue : new Date(),
														emptyText : 'yyyy/mm/dd',
														maskRe : /[0-9\/]/,
														showToday : false
													},
													{
														xtype : 'textfield',
														name : 'fmhname',
														id : 'fmhname',
														fieldLabel : 'Father/Mother/Husband Name:',
														allowBlank : false,
														width : '35%',
														labelWidth : 200,
														maxLength : 25,
														minLength : 5,
														labelStyle : 'white-space: nowrap;'
													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														items : [
																{
																	xtype : 'radiogroup',
																	fieldLabel : 'Gender',
																	id : 'gender',
																	labelWidth : 200,
																	width : '40%',
																	vertical : true,
																	items : [
																			{
																				margin : "0 0 0 30",
																				boxLabel : 'Male',
																				name : 'formtype',
																				inputValue : '1',
																				checked : true
																			},
																			{
																				boxLabel : 'Female',
																				name : 'formtype',
																				inputValue : '2'
																			} ]
																},
																{
																	xtype : 'combo',
																	name : 'marital',
																	id : 'marital',
																	fieldLabel : 'Martial Status:',
																	store : [
																			[
																					'Single',
																					'Single' ],
																			[
																					'Married',
																					'Married' ] ],
																	mode : 'local',
																	triggerAction : 'all',
																	editable : false,
																	value : 'Single',
																	selectOnFocus : true,
																	width : '33%',
																	labelWidth : 200,
																	margin : "0 10 0 55",
																} ]
													} ]
										},
										{
											frame : true,
											bodyStyle : 'padding:5px 5px 5px 5px',
											margin : "5 5 5 5",
											width : '100%',
											title : 'Present Address Detail',
											items : [
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textarea',
																	name : 'presentaddr',
																	id : 'presentaddr',
																	fieldLabel : 'Present Address',
																	allowBlank : false,
																	maxLength : 250,
																	width : '35%',
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	fieldLabel : 'AddrSeq',
																	id : 'addrseq',
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	value : 1,
																	hidden : true
																} ]
													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'state',
																	id : 'state',
																	fieldLabel : 'State',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	name : 'district',
																	id : 'district',
																	fieldLabel : 'District',
																	allowBlank : false,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200
																} ]

													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'pincode',
																	id : 'pincode',
																	fieldLabel : 'Pin Code',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231,
																	regex : /^\d{6}?$/,
																	regexText : 'Pincode should be 6 digits.'
																},
																{
																	xtype : 'textfield',
																	name : 'phonenumber',
																	id : 'phonenumber',
																	fieldLabel : 'Phone Number',
																	allowBlank : false,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	regex : /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
																	maxLength : 15,
																	minLength : 10,
																	regexText : 'Not a valid phone number. Must be in the format 1234567890 or 10 to 15 digits',
																} ]

													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'email',
																	id : 'email',
																	fieldLabel : 'E-mail',
																	vtype : 'email',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231,
																},
																{
																	xtype : 'textfield',
																	name : 'mobilenumber',
																	id : 'mobilenumber',
																	fieldLabel : 'Mobile Number',
																	allowBlank : false,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	regex : /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
																	maxLength : 10,
																	minLength : 10,
																	regexText : 'Not a valid mobile number. Must be in the format 1234567890 or 10 digits',
																} ]

													} ]
										},
										{

											frame : true,
											bodyStyle : 'padding:5px 5px 5px 5px',
											margin : "5 5 5 5",
											width : '100%',
											title : 'Permanent Address Detail',
											items : [
													{
														xtype : 'checkboxfield',
														name : 'chkbx',
														id : 'chkbx',
														scope : this,
														fieldLabel : 'Please check if the present and permanent address are same',
														width : '65%',
														labelWidth : 350,
														listeners : {
															change : function() {
																this
																		.fireEvent('chkbxin');
															}
														}
													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textarea',
																	name : 'permanentaddr',
																	id : 'permanentaddr',
																	fieldLabel : 'Permanent Address',
																	allowBlank : false,
																	maxLength : 250,
																	width : '35%',
																	margin : "5 5 5 0",
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	fieldLabel : 'AddrSeq1',
																	id : 'addrseq1',
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	value : 2,
																	hidden : true
																} ]
													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'state1',
																	id : 'state1',
																	fieldLabel : 'State',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	name : 'district1',
																	id : 'district1',
																	fieldLabel : 'District',
																	allowBlank : false,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200
																} ]

													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'pincode1',
																	id : 'pincode1',
																	fieldLabel : 'Pin Code',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231,
																	regex : /^\d{6}?$/,
																	regexText : 'PinCode should be 6 digits.'
																},
																{
																	xtype : 'textfield',
																	name : 'phonenumber1',
																	id : 'phonenumber1',
																	fieldLabel : 'Phone Number',
																	allowBlank : false,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	regex : /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
																	maxLength : 15,
																	minLength : 10,
																	regexText : 'Not a valid mobile number. Must be in the format 1234567890 or 10 digits',
																} ]

													} ]

										},
										{

											frame : true,
											bodyStyle : 'padding:5px 5px 5px 5px',
											margin : "5 5 5 5",
											width : '100%',
											title : 'Project',
											items : [
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'currentprojectname',
																	id : 'currentprojectname',
																	fieldLabel : 'Project Name',
																	allowBlank : false,
																	maxLength : 250,
																	width : '38%',
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	name : 'designation',
																	id : 'designation',
																	fieldLabel : 'Designation',
																	allowBlank : false,
																	maxLength : 250,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200
																} ]

													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textarea',
																	fieldLabel : 'Role in Project',
																	allowBlank : false,
																	id : 'role',
																	width : '38%',
																	maxLength : 500,
																	labelWidth : 231,
																},
																{
																	xtype : 'textarea',
																	name : 'currentprojdesc',
																	id : 'currentprojdesc',
																	fieldLabel : 'Project Desciption',
																	allowBlank : false,
																	maxLength : 1000,
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200
																} ]
													} ]

										},
										{

											frame : true,
											bodyStyle : 'padding:5px 5px 5px 5px',
											margin : "5 5 5 5",
											width : '100%',
											title : 'Experience',
											items : [
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'company',
																	id : 'company',
																	fieldLabel : 'Company',
																	allowBlank : false,
																	maxLength : 250,
																	width : '38%',
																	labelWidth : 231
																},
																{
																	xtype : 'textfield',
																	fieldLabel : 'Designation',
																	allowBlank : false,
																	id : 'prevdesignation',
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																} ]
													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'datefield',
																	name : 'fromdate',
																	id : 'fromdate',
																	fieldLabel : 'From Date',
																	allowBlank : false,
																	width : '38%',
																	labelWidth : 231,
																	format : 'Y/m/d',
																	altFormats : 'Y/m/d',
																	maxValue : new Date(),
																	emptyText : 'yyyy/mm/dd',
																	maskRe : /[0-9\/]/,
																	listeners : {
																		'select' : function() {
																			this
																					.fireEvent('fromdatevalidatein');
																		}
																	}
																},
																{
																	xtype : 'datefield',
																	name : 'todate',
																	id : 'todate',
																	fieldLabel : 'To Date',
																	allowBlank : false,
																	width : '35%',
																	labelWidth : 200,
																	format : 'Y/m/d',
																	altFormats : 'Y/m/d',
																	maxValue : new Date(),
																	emptyText : 'yyyy/mm/dd',
																	maskRe : /[0-9\/]/,
																	margin : "0 10 0 90",
																	disabled : true,
																	listeners : {
																		'select' : function() {
																			this
																					.fireEvent('todatevalidatein');
																		}
																	}
																} ]

													},
													{
														xtype : 'container',
														layout : 'hbox',
														width : '100%',
														height : '10%',
														margin : "5 5 5 0",
														items : [
																{
																	xtype : 'textfield',
																	name : 'experience',
																	id : 'experience',
																	fieldLabel : 'Experience',
																	readOnly : true,
																	width : '35%',
																	margin : "5 5 5 0",
																	labelWidth : 231,
																},
																{
																	xtype : 'textfield',
																	fieldLabel : 'ExpSeq',
																	id : 'expseq',
																	width : '35%',
																	margin : "0 10 0 90",
																	labelWidth : 200,
																	value : 1,
																	hidden : true
																} ]
													} ]

										} ]
							} ]

				});