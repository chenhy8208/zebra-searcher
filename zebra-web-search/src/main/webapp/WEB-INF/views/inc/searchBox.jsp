<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="form-group text-center">
    <button type="submit" class="btn btn-primary">搜索</button>
    <a id="advanced-search-button" style="display:none;" class="btn btn-default">高级选项</a>
</div>

<div class="form-group advanced-search-panel">
    <div class="panel panel-default">
        <ul class="list-group">

            <!-- add options here -->
            <li class="list-group-item">
                <div class="form-group">
                    <div class="col-xs-4">
                        <label for="search-location">Option 1</label>
                    </div>

                    <div class="col-xs-8">
                        <div class="row">
                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option1" autocomplete="off" checked>
                                Checkbox 1
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="Option 2" autocomplete="off" checked>
                                Checkbox 2
                            </fieldset>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>
            </li>

            <li class="list-group-item">
                <div class="form-group">
                    <div class="col-xs-4">
                        <label for="search-location">Option 2</label>
                    </div>

                    <div class="col-xs-8">
                        <div class="row">
                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option1" autocomplete="off" checked>
                                Checkbox 1
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option2" autocomplete="off" checked>
                                Checkbox 2
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option3" autocomplete="off" checked>
                                Checkbox 3
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option4" autocomplete="off" checked>
                                Checkbox 4
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option5" autocomplete="off" checked>
                                Checkbox 5
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option6" autocomplete="off" checked>
                                Checkbox 6
                            </fieldset>

                            <fieldset class="col-xs-12 col-sm-6">
                                <input type="checkbox" name="search-location" value="option7" autocomplete="off" checked>
                                Checkbox 7
                            </fieldset>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>
            </li>
            <!-- end of options -->
        </ul>

    </div>
</div>
