/*
 * Copyright 2015 XiNGRZ <chenxingyu92@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xingrz.gankmeizhi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends MyBaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.version)
    TextView version;

    @Bind(R.id.libraries)
    RecyclerView libraries;

    @Override
    @SuppressWarnings("SpellCheckingInspection")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);


        //Toolbar 设置点击事情,图标
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);


        //版本的textview
        version.setText(getString(R.string.version, BuildConfig.VERSION_NAME, BuildConfig.FLAVOR));


        //recyclerview的适配器
        LibrariesAdapter adapter = new LibrariesAdapter();

        adapter.add("jgilfelt / SystemBarTint", "https://github.com/jgilfelt/SystemBarTint");
        adapter.add("JakeWharton / butterknife", "https://jakewharton.github.io/butterknife/");
        adapter.add("square / okhttp", "https://square.github.io/okhttp/");
        adapter.add("square / retrofit", "https://square.github.io/retrofit/");
        adapter.add("google / gson", "https://github.com/google/gson");
        adapter.add("bumptech / glide", "https://github.com/bumptech/glide");
        adapter.add("Realm", "https://realm.io");

        libraries.setLayoutManager(new LinearLayoutManager(this));
        libraries.setAdapter(adapter);
    }



    //虽然这是面对 菜单的选择，但是对于  toolbar上的东西，依然成立
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    //打开网页 的intent 应该如何写
    //startActivity(intent);
    private void open(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }






    //设置  recyclerview的适配器
    class LibrariesAdapter extends RecyclerView.Adapter<LibrariesAdapter.ViewHolder> {

        private final ArrayMap<String, String> libs = new ArrayMap<>();

        public void add(String name, String website) {
            libs.put(name, website);
            notifyDataSetChanged();
        }



        //通过 viewType选择其中一个布局产生 view
        //返回一个view给viewholder作为构造函数
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == 0
                    ? new HeaderViewHolder(getLayoutInflater().inflate(R.layout.item_header, parent, false))
                    : new ItemViewHolder(getLayoutInflater().inflate(R.layout.item_library, parent, false));
        }


        //为 viewholder上的控件赋属性值
        @Override
        @SuppressLint("SetTextI18n")
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.getItemViewType() == 0) {
                ((HeaderViewHolder) holder).itemView.setText(
                        position == 0 ? R.string.fork_me_on_github : R.string.libraries_used);
            } else {
                ItemViewHolder itemHolder = (ItemViewHolder) holder;
                if (position == 1) {
                    itemHolder.name.setText("xingrz / GankMeizhi");
                } else {
                    itemHolder.name.setText(libs.keyAt(position - 3));
                }
            }
        }



        //这里估计是，每次调用  onCreateViewHolder时都会 调用这个函数 ，得到的 结果作为  onCreateViewHolder的参数 viewType
        @Override
        public int getItemViewType(int position) {
            return position == 0 || position == 2 ? 0 : 1;
        }

        @Override
        public int getItemCount() {
            return libs.size() + 3;
        }

        private void handleItemClick(int position) {
            if (position == 1) {
                open("https://github.com/xingrz/GankMeizhi");
            } else {
                open(libs.valueAt(position - 3));
            }
        }



















        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }

        }


        class HeaderViewHolder extends ViewHolder {

            TextView itemView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                this.itemView = (TextView) itemView;
            }

        }

        class ItemViewHolder extends ViewHolder {

            @Bind(R.id.name)
            TextView name;

            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(v -> handleItemClick(getAdapterPosition()));
            }

        }

    }

}
