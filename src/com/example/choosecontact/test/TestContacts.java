package com.example.choosecontact.test;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestContacts extends AndroidTestCase {
	Context context;
	
	
	public TestContacts() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetContacts() {
		String s = "abc";
		
		assertTrue(s.equals("abc"));
		
		Cursor cursor;
		
        String[] projection = new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts._ID
        };
        context = this.getContext();
        cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);
		
		assertTrue(cursor != null);
		Log.d("testContacts", "" + cursor.getCount());
		
		String[] columnNames = cursor.getColumnNames();


		
		for (int x = 0; x < cursor.getCount(); x++) {
			cursor.moveToNext();
			for (int i = 0; i < columnNames.length; i++) {
				Log.d(null, columnNames[i] + ": " + cursor.getString(i));
				
			}
			
			if(cursor.getInt(1) > 0){
	            // Query phone here. Covered next
	            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ cursor.getString(2),null, null); 
	            while (phones.moveToNext()) { 
	                //String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	            	int c = 0;
//	            	for(String colName : phones.getColumnNames()) {
//	            		Log.d(null, "ColumnName: " + colName + "  Content: " + phones.getString(c++));
//	            	}
	            	Log.d(null, "type: " + phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)) + " number: " +
	            			phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
	            			);
	            	
	            	Log.d(null, "break--------------------------------------------------");
	            }
	            phones.close(); 
			}
			
			if(cursor.isLast()) break;
		}

		
		
	}
	
}
