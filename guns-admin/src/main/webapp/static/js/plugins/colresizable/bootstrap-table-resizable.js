/**
 * Table Resizable
*Use Plugin: bootstrap-table-resizable
*Dependence: colResizable v1.5
*Usage
*<script src="extensions/cookie/bootstrap-table-resizable.js"></script>
*Options
 *
*resizable
*type: Boolean
*description: Set true to allow the resize in each column.
*default: false
 *
*liveDrag
*type: Boolean
*description: When set to true the table layout is updated while dragging column anchors. liveDrag enabled is more CPU consuming so it is not recommended for slow computers, specially when dealing with huge or extremely complicated tables.
*default: false
 *
*fixed
*type: Boolean
*description: It is used to set how the resize method works. In fixed mode resizing a column does not alter total table width, which means that when a column is expanded the next one shrinks. If fixed is set to false then table can change its width and each column can shrink or expand independently.
*default: true
 *
*headerOnly
*type: Boolean
*description: This attribute can be used to prevent vertical expansion of the column anchors to fit the table height. If it is set to true, column handler's size will be bounded to the first row's vertical size.
*default: false
 *
*minWidth
*type: Integer
*description: This value specifies the minimum width (measured in pixels) that is allowed for the columns.
*default: 15
 *
*hoverCursor
*type: String
*description: This attribute can be used to customize the cursor that will be displayed when the user is positioned on the column anchors.
*default: e-resize
 *
*dragCursor
*type: String
*description: Defines the cursor that will be used while the user is resizing a column.
*default: e-resize
 *
*onResizableResize
*type: Function
*description: If a callback function is supplied it will be fired when the user has ended dragging a column anchor altering the previous table layout. The callback function can obtain a reference to the updated table through the currentTarget attribute of the event retrieved by parameters.
*default: empty function
 *
*onResizableDrag
*type: Function
*description: This event is fired while dragging a column anchor if liveDrag is enabled. It can be useful if the table is being used as a multiple range slider. The callback function can obtain a reference to the updated table through the currentTarget attribute of the event retrieved by parameters
*default: empty function
 * @author: Dennis Hernández
 * @webSite: http://djhvscf.github.io/Blog
 * @version: v1.0.0
 */

(function ($) {
    'use strict';

    var initResizable = function (that) {
        //Deletes the plugin to re-create it
        that.$el.colResizable({disable: true});

        //Creates the plugin
        that.$el.colResizable({
            liveDrag: that.options.liveDrag,
            fixed: that.options.fixed,
            headerOnly: that.options.headerOnly,
            minWidth: that.options.minWidth,
            hoverCursor: that.options.hoverCursor,
            dragCursor: that.options.dragCursor,
            onResize: that.onResize,
            onDrag: that.options.onResizableDrag
        });
    };

    $.extend($.fn.bootstrapTable.defaults, {
        resizable: true,
        liveDrag: false,
        fixed: false,
        headerOnly: false,
        minWidth: 15,
        hoverCursor: 'e-resize',
        dragCursor: 'e-resize',
        onResizableResize: function (e) {
            return false;
        },
        onResizableDrag: function (e) {
            return false;
        }
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _toggleView = BootstrapTable.prototype.toggleView,
        _resetView = BootstrapTable.prototype.resetView;

    BootstrapTable.prototype.toggleView = function () {
        _toggleView.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.resizable && this.options.cardView) {
            //Deletes the plugin
            $(this.$el).colResizable({disable: true});
        }
    };

    BootstrapTable.prototype.resetView = function () {
        var that = this;

        _resetView.apply(this, Array.prototype.slice.apply(arguments));

        if (this.options.resizable) {
            // because in fitHeader function, we use setTimeout(func, 100);
            setTimeout(function () {
                initResizable(that);
            }, 100);
        }
    };

    BootstrapTable.prototype.onResize = function (e) {
        var that = $(e.currentTarget);
        that.bootstrapTable('resetView');
        that.data('bootstrap.table').options.onResizableResize.apply(e);
    }
})(jQuery);