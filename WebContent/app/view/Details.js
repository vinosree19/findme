Ext.Loader.setConfig(
{
	enabled: true
});
Ext.Loader.setPath('Ext.ux', 'ux');
Ext.require([
	'Ext.form.Panel',
	'Ext.ux.form.MultiSelect',
]);
/** Start - Search Tab **/
Ext.regModel('SEARCH',
{
	fields: [
	{
		type: 'string',
		name: 'id'
	},
	{
		type: 'string',
		name: 'username'
	},
	{
		type: 'string',
		name: 'fullname'
	},
	{
		type: 'string',
		name: 'project'
	}]
});
var searchStore = Ext.create('Ext.data.Store',
{
	autoLoad: true,
	model: 'SEARCH',
	data: [],
	reader: new Ext.data.ArrayReader(['id', 'username', 'fullname', 'project'])
});
Ext.define('findme.view.Form1',
{
	extend: 'Ext.form.Panel',
	alias: 'widget.searchform',
	requires: ['Ext.form.field.Text'],
	width: '100%',
	height: '100%',
	defaults:
	{
		bodyStyle: 'padding:5px 5px 5px 5px',
		margin: "10 10 10 10",
	},
	initComponent: function ()
	{
		Ext.apply(this,
		{
			activeRecord: null,
			frame: true,
			title: 'Search Query',
			defaultType: 'textfield',
			bodyPadding: 5,
			width: '100%',
			fieldDefaults:
			{
				anchor: '35%',
				labelAlign: 'left'
			},
			items: [
			{
				fieldLabel: 'User',
				id: 'searchuser',
				name: 'searchuser',
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
				disabled: false
			},
			{
				fieldLabel: 'Project',
				id: 'searchproject',
				name: 'searchproject',
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
				disabled: false
			},
			{
				fieldLabel: 'Designation',
				id: 'searchdesignation',
				name: 'searchdesignation',
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
				disabled: false
			},
			{
				fieldLabel: 'Database',
				id: 'searchdatabase',
				name: 'searchdatabase',
				xtype: 'combo',
				decimalPrecision: 2,
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
				store: dbstore,
				queryMode: 'local',
				displayField: 'name',
				multiSelect: true,
				disabled: false,
				listeners:
				{
					inputEl:
					{
						keydown: function (e)
						{
							e.stopEvent();
						}
					}
				}
			},
			{
				fieldLabel: 'Development',
				id: 'searchdevelopment',
				name: 'searchdevelopment',
				xtype: 'combo',
				decimalPrecision: 2,
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
				store: techstore,
				queryMode: 'local',
				displayField: 'name',
				multiSelect: true,
				disabled: false,
				listeners:
				{
					inputEl:
					{
						keydown: function (e)
						{
							e.stopEvent();
						}
					}
				}
			}],
			dockedItems: [
			{
				xtype: 'toolbar',
				dock: 'bottom',
				items: [
				{
					xtype: 'tbspacer',
					width: '25%'
				},
				{
					itemId: 'search',
					text: 'Search',
					scope: this,
					handler: this.onSearch
				},
				{
					text: 'Reset',
					scope: this,
					handler: this.onResetSearch
				}]
			}]
		});
		this.callParent();
	},
	setActiveRecord: function (record)
	{
		this.activeRecord = record;
		if (record)
		{
			this.down('#search').enable();
			this.getForm().loadRecord(record);
		}
		else
		{
			this.getForm().reset();
		}
	},
	onResetSearch: function ()
	{
		this.setActiveRecord(null);
		this.getForm().reset();
	},
	onSearch: function ()
	{
		if (Ext.getCmp('searchuser').getValue() == '' && Ext.getCmp('searchproject').getValue() == '' && Ext.getCmp(
			'searchdesignation').getValue() == '' && Ext.getCmp('searchdatabase').getValue() == '' && Ext.getCmp(
			'searchdevelopment').getValue() == '')
		{
			Ext.Msg.alert('Error', 'Please enter atleast one search data');
		}
		else
		{
			search();
		}
		this.getForm().reset();
	}
});
Ext.define('findme.view.Grid1',
{
	extend: 'Ext.grid.Panel',
	alias: 'widget.searchgrid',
	width: '100%',
	height: '100%',
	viewConfig:
	{
		emptyText: 'There are no records to display'
	},
	defaults:
	{
		bodyStyle: 'padding:5px 5px 5px 5px',
		margin: "10 10 10 10",
	},
	requires: ['Ext.form.field.Text'],
	initComponent: function ()
	{
		Ext.apply(this,
		{
			frame: true,
			bodyPadding: 5,
			width: '100%',
			layout: 'fit',
			columnLines: true,
			dockedItems: [
			{
				xtype: 'toolbar',
				items: [
				{
					text: 'Print',
					scope: this,
					handler: this.onPrint
				},
				{
					text: 'Clear',
					scope: this,
					handler: this.onClear
				}]
			}],
			columns: [
			{
				header: 'UserId',
				dataIndex: 'id',
				hidden: true,
				hideable: false,
				field:
				{
					type: 'textfield'
				}
			},
			{
				header: 'Username',
				width: '20%',
				sortable: true,
				dataIndex: 'username',
				field:
				{
					type: 'textfield',
					allowBlank: true
				}
			},
			{
				header: 'Full Name',
				width: '50%',
				sortable: true,
				dataIndex: 'fullname',
				field:
				{
					type: 'textfield',
					allowBlank: true
				}
			},
			{
				header: 'Project',
				width: '25%',
				sortable: true,
				dataIndex: 'project',
				field:
				{
					type: 'textarea',
					allowBlank: true
				}
			}]
		});
		this.callParent();
	},
	onPrint: function ()
	{
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection)
		{
			print(selection.id);
		}
	},
	onClear: function ()
	{
		searchStore.removeAll();
	},
	listeners:
	{
		itemdblclick: function (dv, record, item, index, e)
		{
			print(record.id);
		}
	},
});
/** End - Search Tab **/
/** Start - Education Tab **/
Ext.regModel('EDU',
{
	fields: [
	{
		type: 'string',
		name: 'qualification'
	},
	{
		type: 'string',
		name: 'passing'
	},
	{
		type: 'string',
		name: 'institution'
	},
	{
		type: 'string',
		name: 'percentage'
	}]
});
var eduStore = Ext.create('Ext.data.Store',
{
	autoLoad: true,
	model: 'EDU',
	data: [],
	reader: new Ext.data.ArrayReader(['qualification', 'passing', 'institution', 'percentage'])
});
Ext.define('findme.view.Form',
{
	extend: 'Ext.form.Panel',
	alias: 'widget.writerform',
	requires: ['Ext.form.field.Text'],
	width: '100%',
	height: '100%',
	defaults:
	{
		bodyStyle: 'padding:5px 5px 5px 5px',
		margin: "10 10 10 10",
	},
	initComponent: function ()
	{
		Ext.apply(this,
		{
			activeRecord: null,
			frame: true,
			title: 'Education Details',
			defaultType: 'textfield',
			bodyPadding: 5,
			width: '100%',
			fieldDefaults:
			{
				anchor: '35%',
				labelAlign: 'left'
			},
			items: [
			{
				fieldLabel: 'Qualification',
				id: 'qualification',
				name: 'qualification',
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
			},
			{
				fieldLabel: 'Year of Passing',
				id: 'passing',
				name: 'passing',
				xtype: 'numberfield',
				minValue: 1900,
				maxValue: 2100,
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
			},
			{
				fieldLabel: 'Institution',
				id: 'institution',
				name: 'institution',
				xtype: 'textarea',
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
			},
			{
				fieldLabel: 'Percentage',
				id: 'percentage',
				name: 'percentage',
				xtype: 'numberfield',
				decimalPrecision: 2,
				minValue: 0.00,
				maxValue: 100.00,
				allowBlank: true,
				labelWidth: 200,
				margin: "5 5 5 0",
			}],
			dockedItems: [
			{
				xtype: 'toolbar',
				dock: 'bottom',
				items: [
				{
					xtype: 'tbspacer',
					width: '28%'
				},
				{
					itemId: 'add',
					text: 'Add',
					scope: this,
					handler: this.onSave
				},
				{
					text: 'Reset',
					scope: this,
					handler: this.onReset
				}]
			}]
		});
		this.callParent();
	},
	setActiveRecord: function (record)
	{
		this.activeRecord = record;
		if (record)
		{
			this.down('#add').enable();
			this.getForm().loadRecord(record);
		}
		else
		{
			this.getForm().reset();
		}
	},
	onReset: function ()
	{
		this.setActiveRecord(null);
		this.getForm().reset();
	},
	onSave: function ()
	{
		if (eduStore.getCount() < 5 && Ext.getCmp('passing').getValue() >= 1900 && Ext.getCmp('passing').getValue() <= 2100 &&
			Ext.getCmp('percentage').getValue() >= 0 && Ext.getCmp('percentage').getValue() <= 100)
		{
			eduStore.add(
			{
				qualification: Ext.getCmp('qualification').getValue(),
				passing: Ext.getCmp('passing').getValue(),
				institution: Ext.getCmp('institution').getValue(),
				percentage: Ext.getCmp('percentage').getValue()
			});
		}
		else
		{
			Ext.Msg.alert('Error', 'Please Enter the Valid data' + '<br>' + 'Maximum 5 details can add.');
		}
		this.getForm().reset();
	}
});
Ext.define('findme.view.Grid',
{
	extend: 'Ext.grid.Panel',
	alias: 'widget.writergrid',
	width: '100%',
	height: '100%',
	viewConfig:
	{
		emptyText: 'There are no records to display'
	},
	defaults:
	{
		bodyStyle: 'padding:5px 5px 5px 5px',
		margin: "10 10 10 10",
	},
	requires: ['Ext.grid.plugin.CellEditing', 'Ext.form.field.Text',
		'Ext.toolbar.TextItem'
	],
	initComponent: function ()
	{
		this.editing = Ext.create('Ext.grid.plugin.CellEditing');
		Ext.apply(this,
		{
			frame: true,
			bodyPadding: 5,
			width: '100%',
			layout: 'fit',
			columnLines: true,
			plugins: [this.editing],
			dockedItems: [
			{
				xtype: 'toolbar',
				items: [
				{
					text: 'Add',
					scope: this,
					handler: this.onAddClick
				},
				{
					text: 'Delete',
					disabled: true,
					itemId: 'delete',
					scope: this,
					handler: this.onDeleteClick
				},
				{
					text: 'Validate',
					scope: this,
					listeners:
					{
						click: function ()
						{
							educationScreenValidation();
							//this.fireEvent('validatein');
						}
					}
				}]
			}],
			columns: [
			{
				header: 'Qualification',
				width: '20%',
				sortable: true,
				dataIndex: 'qualification',
				field:
				{
					type: 'textfield',
					allowBlank: true
				}
			},
			{
				header: 'Year of Passing',
				width: '20%',
				sortable: true,
				dataIndex: 'passing',
				field:
				{
					xtype: 'numberfield',
					minValue: 1900,
					maxValue: 2100,
					allowBlank: true
				}
			},
			{
				header: 'Institution',
				width: '47%',
				sortable: true,
				dataIndex: 'institution',
				field:
				{
					type: 'textarea',
					allowBlank: true
				}
			},
			{
				header: 'Percentage',
				width: '10%',
				sortable: true,
				dataIndex: 'percentage',
				field:
				{
					xtype: 'numberfield',
					decimalPrecision: 2,
					minValue: 0.00,
					maxValue: 100.00,
					allowBlank: true
				}
			}]
		});
		this.callParent();
		this.getSelectionModel().on('selectionchange', this.onSelectChange,
			this);
	},
	onSelectChange: function (selModel, selections)
	{
		this.down('#delete').setDisabled(selections.length === 0);
	},
	onAddClick: function ()
	{
		if (eduStore.getCount() < 5)
		{
			eduStore.add(
			{
				qualification: '',
				passing: '',
				institution: '',
				percentage: ''
			});
		}
		else
		{
			Ext.Msg.alert('Error', 'Maximum 5 details can add.');
		}
	},
	onDeleteClick: function ()
	{
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection)
		{
			this.store.remove(selection);
		}
	}
});
/** End - Education Tab **/
Ext
	.require(['Ext.data.*', 'Ext.tip.QuickTipManager',
		'Ext.window.MessageBox'
	]);
Ext.require(['Ext.form.*', 'Ext.tip.*']);
Ext.QuickTips.init();
// Define the model for a State
Ext.regModel('DB',
{
	fields: [
	{
		type: 'string',
		name: 'name'
	}]
});
// The data store holding the dbs
var dbstore = Ext.create('Ext.data.Store',
{
	model: 'DB',
	autoLoad: 'true',
	proxy:
	{
		type: 'ajax',
		url: 'app/view/db.json',
		reader:
		{
			type: 'json',
			root: 'success'
		}
	}
});
// The data store holding the technologies
var techstore = Ext.create('Ext.data.Store',
{
	model: 'DB',
	autoLoad: 'true',
	proxy:
	{
		type: 'ajax',
		url: 'app/view/dev.json',
		reader:
		{
			type: 'json',
			root: 'success'
		}
	}
});
//The data store holding the dbs
var dbstore1 = Ext.create('Ext.data.Store',
{
	model: 'DB',
	autoLoad: 'true',
	data: []
});
// The data store holding the technologies
var techstore1 = Ext.create('Ext.data.Store',
{
	model: 'DB',
	autoLoad: 'true',
	data: []
});
Ext
	.define(
		'findme.view.Details',
		{
			extend: 'Ext.form.Panel',
			alias: 'widget.detailview',
			width: '99%',
			//height: '80%',
			height: 825,
			bodyStyle: 'padding:5px 5px 5px 5px',
			margin: "5 10 5 5",
			renderTo: 'find',
			monitorValid: true,
			collapsible: true,
			defaults:
			{
				anchor: '100%'
			},
			items: [
			{
				xtype: 'container',
				layout: 'hbox',
				width: '100%',
				height: '5%',
				margin: "2 0 0 0",
				items: [,
				{
					xtype: 'tbspacer',
					width: '1%'
				},
				{
					xtype: 'displayfield',
					fieldLabel: 'Welcome',
					name: 'user',
					id: 'user',
					margin: "0 0 10 0",
				},
				{
					xtype: 'displayfield',
					id: 'userid',
					hidden: true,
				},
				{
					xtype: 'tbspacer',
					width: '64%'
				},
				{
					xtype: 'button',
					text: 'Save',
					id: 'save',
					formBind: true,
					disabled: true,
					listeners:
					{
						click: function ()
						{
							this.fireEvent('savein');
						}
					}
				},
				{
					xtype: 'tbspacer',
					width: '1%'
				},
				{
					xtype: 'button',
					text: 'Refresh',
					id: 'refresh',
					listeners:
					{
						click: function ()
						{
							this.fireEvent('refreshin');
						}
					}
				},
				{
					xtype: 'tbspacer',
					width: '1%'
				},
				{
					xtype: 'button',
					text: 'Generate',
					id: 'pdf',
					listeners:
					{
						click: function ()
						{
							this.fireEvent('generatein');
						}
					}
				},
				{
					xtype: 'tbspacer',
					width: '1%'
				},
				{
					xtype: 'button',
					text: 'Logout',
					id: 'logout'
				}]
			},
			{
				xtype: 'tabpanel',
				plain: true,
				activeTab: 0,
				height: '100%',
				defaults:
				{
					//bodyStyle: 'padding:5px 5px 5px 5px',
					//margin: "10 10 10 10",
				},
				items: [
				{
					title: 'Employee Information',
					headerPosition: 'top',
					layout: 'accordion',
					id: 'emppanel',
					frame: true,
					width: '100%',
					height: '100%',
					//bodyStyle: 'padding:5px 5px 5px 5px',
					//margin: "10 10 10 10", // Same as
					// CSS
					// ordering
					// (top, right, bottom,
					// left)
					layoutConfig:
					{
						// layout-specific configs go
						// here
						animate: true,
						multi: false,
						activeOnTop: false,
					},
					items: [
					{
						frame: true,
						//bodyStyle: 'padding:5px 5px 5px 5px',
						//margin: "5 5 5 5",
						width: '100%',
						title: 'Employee Personal Information',
						hideCollapseTool: true,
						items: [
						{
							xtype: 'textfield',
							name: 'firstname',
							id: 'firstname',
							fieldLabel: 'First Name',
							allowBlank: false,
							maxLength: 25,
							minLength: 5,
							width: '35%',
							labelWidth: 200
						},
						{
							xtype: 'textfield',
							name: 'middlename',
							id: 'middlename',
							fieldLabel: 'Middle Name',
							allowBlank: true,
							maxLength: 25,
							width: '35%',
							labelWidth: 200
						},
						{
							xtype: 'textfield',
							name: 'lastname',
							id: 'lastname',
							fieldLabel: 'Last Name',
							allowBlank: false,
							maxLength: 25,
							minLength: 5,
							width: '35%',
							labelWidth: 200
						},
						{
							xtype: 'datefield',
							name: 'dob',
							id: 'dob',
							fieldLabel: 'Date of Birth',
							allowBlank: false,
							width: '35%',
							labelWidth: 200,
							format: 'Y/m/d',
							altFormats: 'Y/m/d',
							maxValue: new Date(),
							emptyText: 'yyyy/mm/dd',
							maskRe: /[0-9\/]/,
							showToday: false
						},
						{
							xtype: 'textfield',
							name: 'fmhname',
							id: 'fmhname',
							fieldLabel: 'Father/Mother/Husband Name:',
							allowBlank: false,
							width: '35%',
							labelWidth: 200,
							maxLength: 25,
							minLength: 5,
							labelStyle: 'white-space: nowrap;'
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							items: [
							{
								xtype: 'radiogroup',
								fieldLabel: 'Gender',
								id: 'gender',
								labelWidth: 200,
								width: '40%',
								vertical: true,
								items: [
								{
									margin: "0 0 0 30",
									boxLabel: 'Male',
									name: 'formtype',
									inputValue: '1',
									checked: true
								},
								{
									boxLabel: 'Female',
									name: 'formtype',
									inputValue: '2'
								}]
							},
							{
								xtype: 'combo',
								name: 'marital',
								id: 'marital',
								fieldLabel: 'Martial Status:',
								store: [
									[
										'Single',
										'Single'
									],
									[
										'Married',
										'Married'
									]
								],
								mode: 'local',
								triggerAction: 'all',
								editable: false,
								value: 'Single',
								selectOnFocus: true,
								width: '33%',
								labelWidth: 200,
								margin: "0 10 0 55",
							}]
						}]
					},
					{
						frame: true,
						//bodyStyle: 'padding:5px 5px 5px 5px',
						//margin: "5 5 5 5",
						width: '100%',
						title: 'Present Address Detail',
						items: [
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textarea',
								name: 'presentaddr',
								id: 'presentaddr',
								fieldLabel: 'Present Address',
								allowBlank: false,
								maxLength: 250,
								width: '35%',
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								fieldLabel: 'AddrSeq',
								id: 'addrseq',
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								value: 1,
								hidden: true
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'state',
								id: 'state',
								fieldLabel: 'State',
								allowBlank: false,
								width: '38%',
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								name: 'district',
								id: 'district',
								fieldLabel: 'District',
								allowBlank: false,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'pincode',
								id: 'pincode',
								fieldLabel: 'Pin Code',
								allowBlank: false,
								width: '38%',
								labelWidth: 231,
								regex: /^\d{6}?$/,
								regexText: 'Pincode should be 6 digits.'
							},
							{
								xtype: 'textfield',
								name: 'phonenumber',
								id: 'phonenumber',
								fieldLabel: 'Phone Number',
								allowBlank: false,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								regex: /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
								maxLength: 15,
								minLength: 10,
								regexText: 'Not a valid phone number. Must be in the format 1234567890 or 10 to 15 digits',
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'email',
								id: 'email',
								fieldLabel: 'E-mail',
								vtype: 'email',
								allowBlank: false,
								width: '38%',
								labelWidth: 231,
							},
							{
								xtype: 'textfield',
								name: 'mobilenumber',
								id: 'mobilenumber',
								fieldLabel: 'Mobile Number',
								allowBlank: false,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								regex: /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
								maxLength: 10,
								minLength: 10,
								regexText: 'Not a valid mobile number. Must be in the format 1234567890 or 10 digits',
							}]
						}]
					},
					{
						frame: true,
						//bodyStyle: 'padding:5px 5px 5px 5px',
						//margin: "5 5 5 5",
						width: '100%',
						title: 'Permanent Address Detail',
						items: [
						{
							xtype: 'checkboxfield',
							name: 'chkbx',
							id: 'chkbx',
							scope: this,
							fieldLabel: 'Please check if the present and permanent address are same',
							width: '65%',
							labelWidth: 350,
							listeners:
							{
								change: function ()
								{
									this
										.fireEvent('chkbxin');
								}
							}
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textarea',
								name: 'permanentaddr',
								id: 'permanentaddr',
								fieldLabel: 'Permanent Address',
								allowBlank: false,
								maxLength: 250,
								width: '35%',
								margin: "5 5 5 0",
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								fieldLabel: 'AddrSeq1',
								id: 'addrseq1',
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								value: 2,
								hidden: true
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'state1',
								id: 'state1',
								fieldLabel: 'State',
								allowBlank: false,
								width: '38%',
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								name: 'district1',
								id: 'district1',
								fieldLabel: 'District',
								allowBlank: false,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'pincode1',
								id: 'pincode1',
								fieldLabel: 'Pin Code',
								allowBlank: false,
								width: '38%',
								labelWidth: 231,
								regex: /^\d{6}?$/,
								regexText: 'PinCode should be 6 digits.'
							},
							{
								xtype: 'textfield',
								name: 'phonenumber1',
								id: 'phonenumber1',
								fieldLabel: 'Phone Number',
								allowBlank: false,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								regex: /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
								maxLength: 15,
								minLength: 10,
								regexText: 'Not a valid mobile number. Must be in the format 1234567890 or 10 digits',
							}]
						}]
					},
					{
						frame: true,
						//bodyStyle: 'padding:5px 5px 5px 5px',
						//margin: "5 5 5 5",
						width: '100%',
						title: 'Project',
						items: [
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'currentprojectname',
								id: 'currentprojectname',
								fieldLabel: 'Project Name',
								allowBlank: false,
								maxLength: 250,
								width: '38%',
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								name: 'designation',
								id: 'designation',
								fieldLabel: 'Designation',
								allowBlank: false,
								maxLength: 250,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textarea',
								fieldLabel: 'Role in Project',
								allowBlank: false,
								id: 'role',
								width: '38%',
								maxLength: 500,
								labelWidth: 231,
							},
							{
								xtype: 'textarea',
								name: 'currentprojdesc',
								id: 'currentprojdesc',
								fieldLabel: 'Project Desciption',
								allowBlank: false,
								maxLength: 1000,
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200
							}]
						}]
					},
					{
						frame: true,
						//bodyStyle: 'padding:5px 5px 5px 5px',
						//margin: "5 5 5 5",
						width: '100%',
						title: 'Experience',
						items: [
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'company',
								id: 'company',
								fieldLabel: 'Company',
								allowBlank: false,
								maxLength: 250,
								width: '38%',
								labelWidth: 231
							},
							{
								xtype: 'textfield',
								fieldLabel: 'Designation',
								allowBlank: false,
								id: 'prevdesignation',
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'datefield',
								name: 'fromdate',
								id: 'fromdate',
								fieldLabel: 'From Date',
								allowBlank: false,
								width: '38%',
								labelWidth: 231,
								format: 'Y/m/d',
								altFormats: 'Y/m/d',
								maxValue: new Date(),
								emptyText: 'yyyy/mm/dd',
								maskRe: /[0-9\/]/,
								listeners:
								{
									'select': function ()
									{
										this
											.fireEvent('fromdatevalidatein');
									}
								}
							},
							{
								xtype: 'datefield',
								name: 'todate',
								id: 'todate',
								fieldLabel: 'To Date',
								allowBlank: false,
								width: '35%',
								labelWidth: 200,
								format: 'Y/m/d',
								altFormats: 'Y/m/d',
								maxValue: new Date(),
								emptyText: 'yyyy/mm/dd',
								maskRe: /[0-9\/]/,
								margin: "0 10 0 90",
								disabled: true,
								listeners:
								{
									'select': function ()
									{
										this
											.fireEvent('todatevalidatein');
									}
								}
							}]
						},
						{
							xtype: 'container',
							layout: 'hbox',
							width: '100%',
							height: '10%',
							margin: "5 5 5 0",
							items: [
							{
								xtype: 'textfield',
								name: 'experience',
								id: 'experience',
								fieldLabel: 'Experience',
								readOnly: true,
								width: '35%',
								margin: "5 5 5 0",
								labelWidth: 231,
							},
							{
								xtype: 'textfield',
								fieldLabel: 'ExpSeq',
								id: 'expseq',
								width: '35%',
								margin: "0 10 0 90",
								labelWidth: 200,
								value: 1,
								hidden: true
							}]
						}]
					}, ]
				},
				{
					title: 'Technology',
					width: '100%',
					height: '100%',
					margin: '5 5 5 5',
					items: [
					{
						xtype: 'panel',
						layout: 'hbox',
						width: '100%',
						height: 350,
						margin: "5 5 5 0",
						collapsible: true,
						items: [
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'combo',
							name: 'databases',
							id: 'databases',
							fieldLabel: 'Databases',
							displayField: 'name',
							store: dbstore,
							queryMode: 'local',
							maxLength: 250,
							width: '38%',
							labelWidth: 231,
							typeAhead: true,
							margin: '5 0 0 0',
							listeners:
							{
								'select': function ()
								{
									this
										.fireEvent('dbin');
								}
							}
						},
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'multiselect',
							layout: 'multiselect',
							msgTarget: 'side',
							name: 'multiselect-db',
							id: 'multiselect-db',
							displayField: 'name',
							store: dbstore1,
							margin: '5 0 0 0',
							anchor: '100%',
							height: 350,
							width: '35%',
						},
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'button',
							text: 'Remove',
							id: 'remove',
							margin: '5 0 0 0',
							listeners:
							{
								click: function ()
								{
									var form = Ext.getCmp('multiselect-db');
									dbstore1.removeAt(dbstore1.find('name', form.getValue()));
								}
							}
						},
						{
							xtype: 'tbspacer',
							width: '1%'
						},
						{
							xtype: 'button',
							text: 'ClearAll',
							id: 'clear',
							margin: '5 0 0 0',
							listeners:
							{
								click: function ()
								{
									dbstore1.removeAll();
								}
							}
						}]
					},
					{
						xtype: 'panel',
						layout: 'hbox',
						width: '100%',
						height: 350,
						margin: "5 5 5 0",
						collapsible: true,
						items: [
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'combo',
							name: 'development',
							id: 'development',
							fieldLabel: 'Development',
							width: '38%',
							labelWidth: 231,
							displayField: 'name',
							store: techstore,
							queryMode: 'local',
							margin: '5 0 0 0',
							listeners:
							{
								'select': function ()
								{
									this
										.fireEvent('devin');
								}
							}
						},
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'multiselect',
							layout: 'multiselect',
							msgTarget: 'side',
							name: 'multiselect-dev',
							id: 'multiselect-dev',
							displayField: 'name',
							store: techstore1,
							anchor: '100%',
							height: 350,
							width: '35%'
						},
						{
							xtype: 'tbspacer',
							width: '2%'
						},
						{
							xtype: 'button',
							text: 'Remove',
							id: 'removedev',
							margin: '5 0 0 0',
							listeners:
							{
								click: function ()
								{
									var form = Ext.getCmp('multiselect-dev');
									techstore1.removeAt(techstore1.find('name', form.getValue()));
								}
							}
						},
						{
							xtype: 'tbspacer',
							width: '1%'
						},
						{
							xtype: 'button',
							text: 'ClearAll',
							id: 'cleardev',
							margin: '5 0 0 0',
							listeners:
							{
								click: function ()
								{
									techstore1.removeAll();
								}
							}
						}]
					}]
				},
				{
					title: 'Education',
					width: '100%',
					height: '100%',
					items: [
					{
						itemId: 'form',
						xtype: 'writerform',
					},
					{
						itemId: 'grid',
						stripeRows: true,
						xtype: 'writergrid',
						title: 'Education List',
						store: eduStore,
					}]
				},
				{
					title: 'Search',
					width: '100%',
					height: '100%',
					items: [
					{
						itemId: 'form1',
						xtype: 'searchform',
					},
					{
						itemId: 'grid1',
						stripeRows: true,
						xtype: 'searchgrid',
						title: 'Search Query List',
						store: searchStore,
					}]
				}]
			}]
		});