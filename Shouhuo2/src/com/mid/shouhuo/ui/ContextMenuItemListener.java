package com.mid.shouhuo.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mid.shouhuo.obj.Harvest;
import com.mid.shouhuo.obj.Plan;
import com.mid.shouhuo.util.DialogUtil;

public class ContextMenuItemListener {

	public static OnItemClickListener getListener(final Object obj,
			final MyDialog dialog) {
		if (obj instanceof Plan) {
			if (((Plan) obj).isDaily()) {
				return new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						Plan p = (Plan) obj;

						switch (position) {
						case 0: // ��ɽ��ռƻ�
							p.finish(dialog);
							break;
						case 1: // ������мƻ�
							p.finish(dialog, true);
							break;
						case 2: // �޸ļƻ�
							new DialogUtil(dialog.context())
									.showPlanEditDialog(p);
							dialog.dismiss();
							break;
						case 3:// ɾ���ƻ�
							new DialogUtil(dialog.context())
									.showConfirmDelete(p);
							dialog.dismiss();
							break;
						}

					}

				};
			} else {
				return new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						Plan p = (Plan) obj;

						switch (position) {
						case 0: // ��ɽ��ռƻ�
							p.finish(dialog);
							break;
						case 1: // �޸ļƻ�
							new DialogUtil(dialog.context())
									.showPlanEditDialog(p);
							dialog.dismiss();
							break;
						case 2: // ɾ���ƻ�
							new DialogUtil(dialog.context())
									.showConfirmDelete(p);
							dialog.dismiss();
							break;
						}

					}

				};

			}
		} else if (obj instanceof Harvest) {
			return new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Harvest h = (Harvest) obj;

					switch (position) {
					case 0: // �༭�ջ�
						new DialogUtil(dialog.context())
								.showHarvestEditDialog(h);
						dialog.dismiss();
						break;
					case 1: // ɾ���ջ�
						new DialogUtil(dialog.context()).showConfirmDelete(h);
						dialog.dismiss();
						break;
					}
				}

			};
		}
		return null;
	}

}
