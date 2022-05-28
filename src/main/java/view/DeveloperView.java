package view;

import view.interfaces.GeneralView;

public class DeveloperView implements GeneralView {

    @Override
    public void init() {
        System.out.println("Developer view.");
    }
}
