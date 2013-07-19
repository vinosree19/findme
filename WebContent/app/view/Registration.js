Ext.define('findme.view.Registration',
{
	extend: 'Ext.form.Panel',
	alias: 'widget.registrationview',
	frame: true,
	width: 350,
	bodyPadding: 10,
	bodyBorder: true,
	style: 'marginTop: 250px; marginLeft: 450px;',
	title: 'Account Registration',
	defaults:
	{
		anchor: '100%'
	},
	fieldDefaults:
	{
		labelWidth: 110,
		labelAlign: 'left',
		msgTarget: 'none',
		invalidCls: ''
	},
	/*
	 * Listen for validity change on the entire form and update the combined error icon
	 */
	listeners:
	{
		fieldvaliditychange: function ()
		{
			this.updateErrorState();
		},
		fielderrorchange: function ()
		{
			this.updateErrorState();
		}
	},
	updateErrorState: function ()
	{
		var me = this,
			errorCmp, fields, errors;
		if (me.hasBeenDirty || me.getForm().isDirty())
		{
			errorCmp = me.down('#formErrorState');
			fields = me.getForm().getFields();
			errors = [];
			fields.each(function (field)
			{
				Ext.Array.forEach(field.getErrors(), function (error)
				{
					errors.push(
					{
						name: field.getFieldLabel(),
						error: error
					});
				});
			});
			errorCmp.setErrors(errors);
			me.hasBeenDirty = true;
		}
	},
	config:
	{
		items: [
		{
			xtype: 'textfield',
			name: 'username',
			id: 'user',
			fieldLabel: 'Username',
			allowBlank: false,
			minLength: 5
		},
		{
			xtype: 'textfield',
			name: 'email',
			id: 'email',
			fieldLabel: 'Email Address',
			vtype: 'email',
			allowBlank: false
		},
		{
			xtype: 'textfield',
			name: 'password1',
			id: 'password1',
			fieldLabel: 'Password',
			inputType: 'password',
			style: 'margin-top:15px',
			allowBlank: false,
			minLength: 5
		},
		{
			xtype: 'textfield',
			name: 'password2',
			id: 'password2',
			fieldLabel: 'Repeat Password',
			inputType: 'password',
			allowBlank: false,
			/**
			 * Custom validator implementation - checks that the value matches what was entered into
			 * the password1 field.
			 */
			validator: function (value)
			{
				var password1 = this.previousSibling('[name=password1]');
				return (value === password1.getValue()) ? true : 'Passwords do not match.';
			}
		}],
		dockedItems: [
		{
			cls: Ext.baseCSSPrefix + 'dd-drop-ok',
			xtype: 'container',
			dock: 'bottom',
			layout:
			{
				type: 'hbox',
				align: 'middle'
			},
			padding: '10 10 5 35',
			items: [
			{
				xtype: 'component',
				id: 'formErrorState',
				invalidCls: Ext.baseCSSPrefix + 'form-invalid-icon',
				validCls: Ext.baseCSSPrefix + 'dd-drop-icon',
				baseCls: 'form-error-state',
				flex: 1,
				validText: 'Form is valid',
				invalidText: 'Form has errors',
				tipTpl: Ext.create('Ext.XTemplate', '<ul class="' + Ext.plainListCls +
					'"><tpl for="."><li><span class="field-name">{name}</span>: <span class="error">{error}</span></li></tpl></ul>'
				),
				getTip: function ()
				{
					var tip = this.tip;
					if (!tip)
					{
						tip = this.tip = Ext.widget('tooltip',
						{
							target: this.el,
							title: 'Error Details:',
							minWidth: 200,
							autoHide: false,
							anchor: 'top',
							mouseOffset: [-11, -2],
							closable: true,
							constrainPosition: false,
							cls: 'errors-tip'
						});
						tip.show();
					}
					return tip;
				},
				setErrors: function (errors)
				{
					var me = this,
						tip = me.getTip();
					errors = Ext.Array.from(errors);
					// Update CSS class and tooltip content
					if (errors.length)
					{
						me.addCls(me.invalidCls);
						me.removeCls(me.validCls);
						me.update(me.invalidText);
						tip.setDisabled(false);
						tip.update(me.tipTpl.apply(errors));
					}
					else
					{
						me.addCls(me.validCls);
						me.removeCls(me.invalidCls);
						me.update(me.validText);
						tip.setDisabled(true);
						tip.hide();
					}
				}
			},
			{
				xtype: 'button',
				id: 'submitregistration',
				formBind: true,
				disabled: true,
				text: 'Submit Registration',
				width: 140,
				listeners:
				{
					click: function ()
					{
						var username = Ext.getCmp('user').getValue();
						var password = Ext.getCmp('password1').getValue();
						var email = Ext.getCmp('email').getValue();
						this.fireEvent('registerin', username, password, email);
					}
				}
			}]
		}]
	},
});