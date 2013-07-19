Ext.define('findme.view.Login',
{
	extend: 'Ext.form.Panel',
	alias: 'widget.loginview',
	title: 'Login',
	headerPosition: 'top',
	width: 350,
	height: 180,
	renderTo: 'find',
	frame: true,
	defaultType: 'textfield',
	style: 'marginTop: 250px; marginLeft: 450px;',
	bodyStyle: 'padding:10px 5px 0',
	layout:
	{
		type: "vbox",
		align: "center"
	},
	items: [
	{
		fieldLabel: 'Username',
		name: 'username',
		id: 'username',
		padding: '10',
		allowBlank: false
	},
	{
		fieldLabel: 'Password',
		name: 'password',
		id: 'password',
		inputType: 'password',
		padding: '5',
		allowBlank: false
	},
	{
		xtype: 'container',
		flex: 1,
		layout: 'hbox',
		padding: '5',
		width: 250,
		height: 25,
		items: [
		{
			xtype: 'button',
			text: 'Login',
			id: 'loginnow',
			margin: "0 0 0 80",
			listeners:
			{
				click: function ()
				{
					var username = Ext.getCmp('username').getValue();
					var password = Ext.getCmp('password').getValue();
					this.fireEvent('loginin', username, password);
				}
			}
		},
		{
			xtype: 'box',
			id: 'registernow',
			autoEl:
			{
				tag: 'a',
				href: '#',
				html: 'Register Now?'
			},
			listeners:
			{
				afterrender: function ()
				{
					this.fireEvent('signin');
				}
			},
			margin: "3 0 0 10"
		}]
	}]
});