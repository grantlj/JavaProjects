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
						case 0: // 完成今日计划
							p.finish(dialog);
							break;
						case 1: // 完成所有计划
							p.finish(dialog, true);
							break;
						case 2: // 修改计划
							new DialogUtil(dialog.context())
									.showPlanEditDialog(p);
							dialog.dismiss();
							break;
						case 3:// 删除计划
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
						case 0: // 完成今日计划
							p.finish(dialog);
							break;
						case 1: // 修改计划
							new DialogUtil(dialog.context())
									.showPlanEditDialog(p);
							dialog.dismiss();
							break;
						case 2: // 删除计划
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
					case 0: // 编辑收获
						new DialogUtil(dialog.context())
								.showHarvestEditDialog(h);
						dialog.dismiss();
						break;
					case 1: // 删除收获
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
