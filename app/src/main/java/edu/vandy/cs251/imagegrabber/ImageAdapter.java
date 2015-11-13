package edu.vandy.cs251.imagegrabber;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * This is the adapter that shows the content to the recycler view
 *
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    /**
     * List of images to be displayed in the recycler view
     */
    private List<Image> mImageList;

    /**
     * Context to display the list to
     */
    private Context mContext;

    /**
     * Constructs the adapter
     * @param mContext The context where the adapter is created / called
     * @param mImageList The list of images to be displayed
     */
    public ImageAdapter(Context mContext, List<Image> mImageList) {
        this.mImageList = mImageList;
        this.mContext = mContext;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image_view, parent, false);
        return new ImageViewHolder(view);
    }

    /**
     * This method defines all the characterstics of a single row in the recycler view i.e.
     * What to display in different views of the row and what to do when the user interacts
     * with the particular views within the row!
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Image currImage = mImageList.get(position);
        if (currImage != null){
            holder.mImageNameView.setText(currImage.getUri().getLastPathSegment());
            holder.mDownloadTimeView.setText("" + (currImage.downloadTime/1000) + " sec");
            holder.mImageView.setImageURI(currImage.getUri());
            // When user clicks the view button, image is launched in the gallery
            holder.mViewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(currImage.getUri(), "image/*");
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * Is used to get the total number of images to be shown in the view
     * @return Number of images in the list
     */
    @Override
    public int getItemCount() {
        return (mImageList == null)? 0 :mImageList.size();
    }

    /**
     * This is the class that represents the ViewHolder i.e one of the
     * row of the recycler view
     */
    class ImageViewHolder extends RecyclerView.ViewHolder{
        private TextView mImageNameView;   // view that displays the name of the image
        private ImageView mImageView;   // view that shows the preview of the image
        private TextView mDownloadTimeView; // view that displays the download time
        private Button mViewBtn;  // button that launches the gallery when clicked by the user
        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageNameView = (TextView) itemView.findViewById(R.id.imageTitle);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mDownloadTimeView = (TextView) itemView.findViewById(R.id.downloadTime);
            mViewBtn = (Button) itemView.findViewById(R.id.viewInGalaryBtn);
        }
    }
}
