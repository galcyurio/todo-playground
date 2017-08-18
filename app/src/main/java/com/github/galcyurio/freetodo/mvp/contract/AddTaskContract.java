package com.github.galcyurio.freetodo.mvp.contract;

import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.mvp.BasePresenter;
import com.github.galcyurio.freetodo.mvp.BaseView;

/**
 * @author galcyurio
 */
public interface AddTaskContract {

    interface View extends BaseView {

        void showNotSavedMessage();

        void showSuccessMessage();

        void showFailedMessage();

        void showTaskListUi();
    }

    interface Presenter extends BasePresenter {

        void onWriteBtnClickEvent(Events.WriteBtnClickEvent event);
    }

}
