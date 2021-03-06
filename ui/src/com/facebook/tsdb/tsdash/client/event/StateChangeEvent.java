/*
 * Copyright 2011 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.tsdb.tsdash.client.event;

import com.facebook.tsdb.tsdash.client.model.ApplicationState;
import com.google.gwt.event.shared.GwtEvent;

public class StateChangeEvent extends GwtEvent<StateChangeHandler> {

    public static final GwtEvent.Type<StateChangeHandler> TYPE =
        new GwtEvent.Type<StateChangeHandler>();

    public enum StateChange {
        VIEW, METRIC, TIME, PLOT, AUTORELOAD, SCREEN;
    }

    private final StateChange change;
    private final ApplicationState appState;

    public StateChangeEvent(StateChange change, ApplicationState appState) {
        this.change = change;
        this.appState = appState;
    }

    public ApplicationState getAppState() {
        return appState;
    }

    @Override
    public GwtEvent.Type<StateChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(StateChangeHandler handler) {
        if (change.equals(StateChange.VIEW)) {
            handler.onViewChange(this);
        } else if (change.equals(StateChange.METRIC)) {
            handler.onMetricChange(this);
        } else if (change.equals(StateChange.TIME)) {
            handler.onTimeChange(this);
        } else if (change.equals(StateChange.PLOT)) {
            handler.onPlotParamsChange(this);
        } else if (change.equals(StateChange.SCREEN)) {
            handler.onScreenChange(this);
        }
    }

}
