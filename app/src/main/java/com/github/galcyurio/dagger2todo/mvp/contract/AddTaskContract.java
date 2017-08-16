package com.github.galcyurio.dagger2todo.mvp.contract;

import com.github.galcyurio.dagger2todo.mvp.BasePresenter;
import com.github.galcyurio.dagger2todo.mvp.BaseView;

/**
 * @author galcyurio
 */
public interface AddTaskContract {

    interface View extends BaseView {

        void showTaskListUi();
    }

    interface Presenter extends BasePresenter {

    }

}
