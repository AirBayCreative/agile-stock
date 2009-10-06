package hk.reality.stock;

import hk.reality.stock.model.Stock;
import hk.reality.stock.model.StockDetail;
import hk.reality.stock.view.StockAdapter;

import java.math.BigDecimal;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class PortfolioActivity extends ListActivity {
    private static final String TAG = "PortfolioActivity";
    private StockAdapter adapter;
    
    private static final int DIALOG_ADD_STOCK = 100; 
    private static final int DIALOG_EDIT_VIEW = 1200000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        adapter = new StockAdapter(this);
        setListAdapter(adapter);
        
        Stock stock = new Stock();
        stock.setQuote("0005");
        
        StockDetail d = new StockDetail();
        d.setQuote("0005");
        d.setChangePrice(new BigDecimal("+1.2"));
        d.setChangePricePercent(new BigDecimal("+0.8"));
        d.setPrice(new BigDecimal("88.6"));
        d.setName("匯豐控股");
        d.setUpdatedAt(Calendar.getInstance());
        stock.setDetail(d);

        adapter.add(stock);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.getItem(0).setIcon(R.drawable.ic_menu_rotate);
        menu.getItem(1).setIcon(R.drawable.ic_menu_add);        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.refresh:
            return true;
        case R.id.add:
            this.showDialog(DIALOG_ADD_STOCK);
            return true;
        default:
        }
        return false;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DIALOG_ADD_STOCK:
            final EditText input = new EditText(this);
            input.setId(DIALOG_EDIT_VIEW);
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Stock Quote")
                .setMessage("Please enter a Hong Kong Stock Quote (e.g. 00001)")
                .setCancelable(true)
                .setPositiveButton("OK", new OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        String value = input.getText().toString();
                        Log.d(TAG, "entered quote: " + value);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Log.d(TAG, "cancelled");
                    }
                })
                .setView(input)
                .create();
            return dialog;
        default:
        }
        return null;
    }
    
    @Override
    protected void onPrepareDialog(int id, Dialog d) {
        switch (id) {
        case DIALOG_ADD_STOCK:
            EditText input = (EditText) d.findViewById(DIALOG_EDIT_VIEW);;  
            input.setText("");
        default:
        }
    }
}