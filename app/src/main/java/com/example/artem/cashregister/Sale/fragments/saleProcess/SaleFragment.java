package com.example.artem.cashregister.Sale.fragments.saleProcess;


import android.arch.lifecycle.Observer;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artem.cashregister.R;
import com.example.artem.cashregister.Sale.LocalRepository;
import com.example.artem.cashregister.Sale.ProductCursorAdapter;
import com.example.artem.cashregister.Sale.ProductsListViewAdapter;;
import com.example.artem.cashregister.Sale.fragments.keyboard.FreeGoods;
import com.example.artem.cashregister.dataBase.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



public class SaleFragment extends Fragment {

    public interface SaleFragmentListener{
        void requestAddDialogFragment();
    }

    private SearchView searchView;
    private ListView listView;
    private LocalRepository localRepository = new LocalRepository();
    SaleFragmentListener mListener;
    Double totalAmount = 0.00;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sale, container,false);

        ImageView addProduct = view.findViewById(R.id.fragment_sale__button_add_product_in_list);
        addProduct.setOnClickListener(new OnAddClicked());

        LinearLayout addFreeProduct = view.findViewById(R.id.fragment_sale__button_add_free_product);
        addFreeProduct.setOnClickListener(new OnButtonAddProductClicked());

        TextView totalAmout = (TextView) view.findViewById(R.id.fragment_sale__result_figure);

        String parsedIntoStringAmount = String.valueOf(totalAmount);
        totalAmout.setText(parsedIntoStringAmount);

        searchView = (SearchView) view.findViewById(R.id.fragment_sale__search_product_in_database);
        listView = (ListView) view.findViewById(R.id.fragment_sale__suggestions_from_db);


        searchView.setOnQueryTextListener(onQueryTextListener);

        return view;
    }

    //Implementing of search feature

    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            query = "%"+query+"%";
            localRepository.getProductListInfo(getActivity(),query)
                    .observe(getActivity(),new Observer<List<Product>>(){
                        @Override
                        public void onChanged(@Nullable List<Product> products) {
                            if(products == null){
                                return;
                            }
                            ProductsListViewAdapter adapter = new ProductsListViewAdapter(
                                    getActivity(),
                                    R.layout.item_of_search_suggestion_view_holder,products);
                            listView.setAdapter(adapter);
                        }
                    });
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            getProductsFromDb(newText);
            return true;
        }

        private void getProductsFromDb(String searchText){
            searchText = "%"+searchText+"%";
            Observable.just(searchText).observeOn(Schedulers.computation())
                    .map(new io.reactivex.functions.Function<String, Cursor>(){
                @Override
                public Cursor apply(String input) throws Exception {
                    return localRepository.getProductsCursor(getActivity(),input);
                }
            }).observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<Cursor>(){
                  @Override
                  public void accept(Cursor cursor) throws Exception {
                        handleResults(cursor);
                  }
              },new Consumer<Throwable>(){
                  @Override
                  public void accept(Throwable throwable)throws Exception {
                      handleError(throwable);
                  }
              });
        }
    };

    private void handleResults(Cursor cursor){
        searchView.setSuggestionsAdapter(new ProductCursorAdapter(getActivity(),cursor,searchView));
    }

    private void handleError(Throwable t){
        Toast.makeText(getActivity(), "Problem in Fetching Product",
                Toast.LENGTH_LONG).show();
    }

   //end of search feature implementing

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (SaleFragmentListener) context;
    }

    public class OnAddClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mListener.requestAddDialogFragment();
        }
    }

    public class OnButtonAddProductClicked implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FreeGoods freeGoodsFragment = new FreeGoods();
            fragmentTransaction.replace(R.id.activity_sale__container_of_called_fragment,freeGoodsFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


}
