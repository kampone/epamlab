package by.bsu.service.action;


import by.bsu.service.command.common.AuthorizationCommand;
import by.bsu.service.command.LogoutCommand;
import by.bsu.service.command.admin.*;
import by.bsu.service.command.common.ChangeLanguageCommand;
import by.bsu.service.command.common.DishMenuCommand;
import by.bsu.service.command.common.LoginCommand;
import by.bsu.service.command.cook.DishReadyCommand;
import by.bsu.service.command.cook.ViewCookOrdersCommand;
import by.bsu.service.command.waiter.*;

/**
 * Enum of all command
 * @see ActionCommand
 */
public enum CommandEnum {
    LOGIN
    {
        {
            this.command = new LoginCommand();
        }
    },
    DISHMENU
            {
                {
                    this.command = new DishMenuCommand();
                }
            },
    LOGOUT

    {
        {
            this.command = new LogoutCommand();
        }
    },
    CHANGE_LANGUAGE{
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    AUTH{
        {
            this.command = new AuthorizationCommand();
        }
    },
    NEW_USER{
        {
            this.command = new NewUserCommand();
        }

    },
    READY_EDIT_ORDER{
        {
            this.command = new ReadyEditOrderCommand();
        }
    },
    CREATE_ORDER{
        {
            this.command = new OrderCreateCommand();
        }
    },
    CREATE_USER{
        {
            this.command = new UserCreateCommand();
        }
    },
    DEL_USER{
        {
            this.command = new UserDeleteCommand();
        }
    },
    DEL_CURRENT_USER{
        {
            this.command = new DeleteUserCommand();
        }

    },
    ADD_DISH{
        {
            this.command = new AddDishCommand();
        }
    },
    COOK_ORDERS{
        {
            this.command = new ViewCookOrdersCommand();
        }
    },
    WAITER_ORDERS{
        {
            this.command = new ViewWaiterOrdersCommand();
        }
    },
    ADMIN_ORDERS{
        {
            this.command = new ViewAdminOrdersCommand();
        }
    },
    CONFIRM_ORDER{
        {
            this.command = new ConfirmOrderCommand();
        }
    },
    DISH_READY{
        {
            this.command = new DishReadyCommand();
        }
    },
    ORDER_REPORT{
        {
            this.command = new OrderReportCommand();
        }
    },
    PAY_ORDER{
        {
            this.command = new PayOrderCommand();
        }
    },
    CANCEL_ORDER{
        {
            this.command = new CancelOrderCommand();
        }
    },
    CANCEL_DISH{
        {
            this.command = new CancelOrderDetailCommand();
        }
    },
    EDIT_ORDER{
        {
            this.command = new EditOrderCommand();
        }
    },
    NOT_CONFIRM_ORDER{
        {
            this.command = new NotConfirmOrderCommand();
        }

    };
    ActionCommand command;


    public ActionCommand getCurrentCommand() {
        return command;
    }
}
