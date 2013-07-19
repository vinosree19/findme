Ext.application(
{
	name: 'findme',
	appFolder: 'app',
	views: ['Login', 'Registration', 'Details'],
	controllers: ['MyController'],
	launch: function ()
	{
		Ext.create('Ext.container.Viewport',
		{
			items: [
			{
				xtype: 'loginview',
				title: 'Login'
			}]
		});
	}
});