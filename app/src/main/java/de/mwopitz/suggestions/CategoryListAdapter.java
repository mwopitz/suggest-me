package de.mwopitz.suggestions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.mwopitz.suggestions.appdata.Category;
import de.mwopitz.suggestions.databinding.CategoryListItemBinding;

class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private static final String TAG = CategoryListAdapter.class.getSimpleName();

    private final List<Category> mCategories;
    private OnCategoryClickListener mOnCategoryClickListener;

    CategoryListAdapter() {
        this.mCategories = new ArrayList<>();
    }

    @Override
    @NonNull
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryListItemBinding binding = CategoryListItemBinding.inflate(inflater, parent, false);

        binding.getRoot().setOnClickListener(view -> {
            if (mOnCategoryClickListener != null) {
                mOnCategoryClickListener.onCategoryClick(binding.getCategory());
            }
        });

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    void setCategories(@NonNull final List<Category> categories) {
        if (mCategories.size() > 0) {
            Log.i(TAG, "Replacing non-empty category list content");
        }
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    void setOnCategoryClickListener(OnCategoryClickListener listener) {
        mOnCategoryClickListener = listener;
    }

    interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CategoryListItemBinding binding;

        ViewHolder(CategoryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Category category) {
            binding.setCategory(category);
            binding.executePendingBindings(); // run bindings immediately (instead of next frame)
        }
    }
}
