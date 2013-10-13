package com.mid.shouhuo.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mid.shouhuo.R;

public class MyDialog extends Dialog {

	private final static String TAG = "[MyDialog]";

	private Context context;

	public MyDialog(Context context) {
		super(context);
		this.context = context;
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public MyDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
	}

	public Context context() {
		return this.context;
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;

		private int style = -1;
		private int layout = -1;

		private MyDialog dialog;
		private View rootLayout;

		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public Builder setStyle(int s) {
			this.style = s;
			return this;
		}

		public Builder setLayout(int layout) {
			this.layout = layout;
			return this;
		}

		public Builder setContentView(int layout) {

			View v = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(layout, null);
			this.setContentView(v);
			return this;
		}

		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * Create the custom dialog
		 */
		@SuppressWarnings("deprecation")
		public MyDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme

			if (style != -1) {
				dialog = new MyDialog(context, this.style);
			} else {
				dialog = new MyDialog(context, R.style.MyDialog);
			}

			if (layout != -1) {
				rootLayout = inflater.inflate(layout, null);
			} else {
				rootLayout = inflater.inflate(R.layout.dialog_layout, null);
			}

			dialog.addContentView(rootLayout, new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			TextView title_text = (TextView) rootLayout
					.findViewById(R.id.dialog_title);
			if (title_text != null) {
				((TextView) rootLayout.findViewById(R.id.dialog_title))
						.setText(title);
			}

			if (layout != R.layout.dialog_context_menu) {

				// set the confirm button
				if (positiveButtonText != null) {
					((Button) rootLayout.findViewById(R.id.dialog_pos_btn))
							.setText(positiveButtonText);
					if (positiveButtonClickListener != null) {
						((Button) rootLayout.findViewById(R.id.dialog_pos_btn))
								.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										positiveButtonClickListener
												.onClick(
														dialog,
														DialogInterface.BUTTON_POSITIVE);
									}
								});
					}
				} else {
					rootLayout.findViewById(R.id.dialog_pos_btn).setVisibility(
							View.GONE);
				}

				if (negativeButtonText != null) {
					((Button) rootLayout.findViewById(R.id.dialog_neg_btn))
							.setText(negativeButtonText);
					if (negativeButtonClickListener != null) {
						((Button) rootLayout.findViewById(R.id.dialog_neg_btn))
								.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										negativeButtonClickListener
												.onClick(
														dialog,
														DialogInterface.BUTTON_NEGATIVE);
									}
								});
					}
				} else {

					rootLayout.findViewById(R.id.dialog_neg_btn).setVisibility(
							View.GONE);
				}
			}
			// set the content message
			if (message != null) {
				Log.v(TAG, "单行消息对话框");
				((TextView) rootLayout.findViewById(R.id.dialog_message))
						.setText(message);
				/*
				 * if (c != null) { Log.v(TAG, "参数c:" + c); id = c.get_id(); }
				 * else { Toast.makeText(context,
				 * context.getText(R.string.toast_dialog_no_id),
				 * Toast.LENGTH_SHORT).show(); }
				 */

			} else if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((LinearLayout) rootLayout.findViewById(R.id.dialog_content))
						.removeAllViews();
				((LinearLayout) rootLayout.findViewById(R.id.dialog_content))
						.addView(contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));

				/*
				 * nameView = (EditText) layout.findViewById(R.id.dialog_name);
				 * numberView = (EditText)
				 * layout.findViewById(R.id.dialog_number); if (c != null) {
				 * Log.v(TAG, "edit参数c:" + c); id = c.get_id();
				 * nameView.setText(c.getName());
				 * numberView.setText(c.getNumber()); }
				 */
			}
			dialog.setContentView(rootLayout);

			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Window dialogWindow = dialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			dialogWindow.setGravity(Gravity.CENTER);
			// lp.width = (int) (display.getWidth() * 0.9);
			/*Point p = new Point();
			display.getSize(p);*/
			/*if (layout != -1) {
				lp.width = (int) (p.x * 0.8);
			} else {
				lp.width = (int) (p.x * 0.9);
			}*/
			lp.width = (int) (display.getWidth() * 0.9);
			if (layout == R.layout.dialog_set_time_layout) {
				lp.width = (int) (display.getWidth());
			}
			// lp.y -= 20;
			dialogWindow.setAttributes(lp);
			if (layout != R.layout.dialog_context_menu) {
				dialog.setCancelable(false);
			} else {
				dialog.setCancelable(true);
			}
			return dialog;
		}

		public View getContentView() {
			return contentView;
		}

	}

}
